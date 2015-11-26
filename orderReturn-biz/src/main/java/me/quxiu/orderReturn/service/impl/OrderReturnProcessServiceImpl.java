package me.quxiu.orderReturn.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.quxiu.orderReturn.dto.ExpressInfoDto;
import me.quxiu.orderReturn.dto.OrderItemDto;
import me.quxiu.orderReturn.dto.OrderReturnDto;
import me.quxiu.orderReturn.dto.RefundCalculateDto;
import me.quxiu.orderReturn.dto.ReturnApplyDto;
import me.quxiu.orderReturn.dto.ReturnProcessLogDto;
import me.quxiu.orderReturn.mapper.OrderReturnDetailMapper;
import me.quxiu.orderReturn.mapper.OrderReturnExpressCouponMapper;
import me.quxiu.orderReturn.mapper.OrderReturnHeaderMapper;
import me.quxiu.orderReturn.mapper.OrderReturnProcessLogMapper;
import me.quxiu.orderReturn.mapper.OrderReturnTypeMapper;
import me.quxiu.orderReturn.mapper.VeExpressMapper;
import me.quxiu.orderReturn.model.OrderReturnDetail;
import me.quxiu.orderReturn.model.OrderReturnHeader;
import me.quxiu.orderReturn.model.OrderReturnProcessLog;
import me.quxiu.orderReturn.model.OrderReturnType;
import me.quxiu.orderReturn.model.VeExpress;
import me.quxiu.orderReturn.query.OrderReturnHeaderQuery;
import me.quxiu.orderReturn.service.OrderReturnProcessService;
import me.quxiu.orderReturn.util.DateFormatUtil;
import me.quxiu.orderReturn.util.UDFQxDecrypt;
import me.quxiu.share.result.Reason;
import me.quxiu.share.result.ResultEntity;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客退流程Service
 * 
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年11月5日 上午11:26:13
 * 
 */

@Service("orderReturnProcessService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED , isolation= Isolation.READ_COMMITTED ,rollbackFor = { Exception.class })
public class OrderReturnProcessServiceImpl implements OrderReturnProcessService{

	public static final String IMAGE_PRE_HTTP_URL = "http://img.quxiu.me";
	
	@Autowired
	OrderReturnTypeMapper orderReturnTypeMapper;
	@Autowired
	OrderReturnHeaderMapper orderReturnHeaderMapper;
	@Autowired
	OrderReturnDetailMapper orderReturnDetailMapper;
	@Autowired
	OrderReturnProcessLogMapper orderReturnProcessLogMapper;
	@Autowired
	VeExpressMapper veExpressMapper;
	@Autowired
	OrderReturnExpressCouponMapper orderReturnExpressCouponMapper;
	
	@Override
	public ResultEntity<OrderItemDto> getOrderItemList(Integer orderId) {
		ResultEntity<OrderItemDto> result = new ResultEntity<OrderItemDto>();
		
		int orderCount = orderReturnHeaderMapper.getOrderCountByOrderId(orderId);
		
		//returnAttribute 支持退换属性（1-可以退换，2-已经申请退货，3-不支持退货）
		//原始订单的商品明细
		List<OrderItemDto> itemList = orderReturnTypeMapper.getOrderItemByOrderId(orderId);
		if(CollectionUtils.isEmpty(itemList) || orderId == null || orderId == 0 || orderCount<1){
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("订单ID不存在,orderId:"+orderId);
			return result;
		}
		
		//修改商品图片相对路径为绝对路径
		for(OrderItemDto item : itemList){
			if(StringUtils.isNotBlank(item.getImgUrl())){
				item.setImgUrl(IMAGE_PRE_HTTP_URL+item.getImgUrl().substring(1));
			}
		}
		
		//已经申请退货的商品明细
		List<OrderItemDto> detailList = orderReturnDetailMapper.getOrderReturnItemByOrderId(orderId);
		Iterator<OrderItemDto> iterator = detailList.iterator();
		while (iterator.hasNext()) {
			OrderItemDto detail = iterator.next();
			if(StringUtils.isBlank(detail.getSkuCode())){//不存在退货商品
				iterator.remove();continue;
			}
			
			if(StringUtils.isNotBlank(detail.getImgUrl())){
				detail.setImgUrl(IMAGE_PRE_HTTP_URL+detail.getImgUrl().substring(1));
			}
			for(OrderItemDto item : itemList){
				//支持14天无理由退货的商品、减去已经申请退货数量
				if(item.getReturnAttribute()==1 && detail.getSkuCode().equals(item.getSkuCode())){
					item.setQty(item.getQty() - detail.getQty());
				}
			}
		}
		itemList.addAll(detailList);
		
		//移出已经没有可以退货的sku
		Iterator<OrderItemDto> itemIterator = itemList.iterator();
		while (itemIterator.hasNext()) {
			OrderItemDto item = itemIterator.next(); 
			if(item.getReturnAttribute()==1 && item.getQty()<=0){
				itemIterator.remove();
			}
		}
		
		result.getResponseBody().setDatas(itemList);
		return result;
	}


	@Override
	public ResultEntity<OrderReturnType> getReturnReasonList() {
		ResultEntity<OrderReturnType> result = new ResultEntity<OrderReturnType>();
		result.getResponseBody().setDatas(orderReturnTypeMapper.getAll());
		return result;
	}


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED , isolation= Isolation.READ_COMMITTED ,rollbackFor = { Exception.class })
	public ResultEntity<OrderReturnDto> saveReturnApply(ReturnApplyDto applyDto) {
		ResultEntity<OrderReturnDto> result = new ResultEntity<OrderReturnDto>();
		
		int orderCount = orderReturnHeaderMapper.getOrderCountByOrderId(applyDto.getOrderId());
		if(orderCount == 0 || applyDto.getOrderId()==0){
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("订单ID不存在,orderId:"+applyDto.getOrderId());
			return result;
		}
		
		//判断退货商品skuCode属于原订单
		String errorMsg = isSkuCodeError(applyDto);
		if(StringUtils.isNotBlank(errorMsg)){
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("客退申请失败,退货商品skuCode："+ errorMsg +"，不属于订单，orderId:"+applyDto.getOrderId());
			return result;
		}
		
		//判断是否超出订单商品数退货
		if(isOverReturn(applyDto)){
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("客退申请失败,已超出商品数量申请退货，orderId:"+applyDto.getOrderId());
			return result;
		}
		
		OrderReturnHeader header = new OrderReturnHeader();
		header.setReturnNo(String.valueOf(System.currentTimeMillis()));
		header.setImgUrl1(applyDto.getImgUrl1());
		header.setImgUrl2(applyDto.getImgUrl2());
		header.setImgUrl3(applyDto.getImgUrl3());
		header.setOrderId(applyDto.getOrderId());
		header.setOrderSn(applyDto.getOrderSn());
		header.setReturnDescript(applyDto.getReturnDescript());
		header.setUserId(applyDto.getUserId());
		header.setReturnMoney(applyDto.getRefundTotal());
		
		header.setStatusAndMsg(OrderReturnHeader.STATUS_INIT,null,null,0);
		header.setIsSend(0);
		header.setSendTime(null);
		header.setSendTimes(0);
		header.setIsDelete(false);
		header.setCreateTime(new Date());
		header.setUpdateTime(new Date());
		header.setCreateBy(applyDto.getUserId()+StringUtils.EMPTY);
		orderReturnHeaderMapper.save(header);
		
		if(CollectionUtils.isNotEmpty(applyDto.getDetails())){
			for(OrderItemDto item : applyDto.getDetails()){
				OrderReturnDetail detail = new OrderReturnDetail();
				
				String poNumb = orderReturnTypeMapper.getPoNumberByOrderIdAndSkuCode(applyDto.getOrderId(), item.getSkuCode());
				detail.setPoNo(poNumb);//PO单号
				HashMap<String,Object> map = orderReturnTypeMapper.getSupplierIdAndDiscount(applyDto.getOrderId(), item.getSkuCode());
				if(map!=null && map.get("supplier_id") != null){//供应商Id
					detail.setSupplierId(Integer.valueOf(map.get("supplier_id").toString()));
				}
				if(map!=null && map.get("discount") != null){//sku扣点
					detail.setDiscount(new BigDecimal(map.get("discount").toString()));
				}
				
				detail.setQty(item.getApplyQty());
				detail.setReturnReason(item.getReasonCode());
				detail.setSkuCode(item.getSkuCode());
				detail.setSkuName(item.getSkuName());
				detail.setTotalPrice(item.getTotalPrice());
				detail.setUnitPrice(item.getUnitPrice());
				
				detail.setOrderReturnHeaderId(header.getId());
				detail.setIsDelete(false);
				detail.setCreateTime(new Date());
				detail.setUpdateTime(new Date());
				detail.setCreateBy(header.getUserId()+StringUtils.EMPTY);
				orderReturnDetailMapper.save(detail);
			}
		}
		
		//updateDealOrderAfterSale(applyDto);
		//修改原始订单售后服务状态、已经申请过退货
		orderReturnTypeMapper.updateDealOrderAllReturned(applyDto.getOrderId());
		
		OrderReturnProcessLog log = new OrderReturnProcessLog();
		log.setReturnNo(header.getReturnNo());
		log.setStatusAndMsg(OrderReturnHeader.STATUS_INIT,null,null,0);
		
		//会员填写客退申请
		if(applyDto.getApplyUserId() == applyDto.getUserId()){
			log.setProcessUser(UDFQxDecrypt.qxDecrypt(orderReturnTypeMapper.getUserNameById(applyDto.getUserId())));
			log.setCreateBy(applyDto.getUserId()+StringUtils.EMPTY);
			log.setUpdateBy(applyDto.getUserId()+StringUtils.EMPTY);
			//客服填写客退申请	
		}else{
			log.setProcessUser(UDFQxDecrypt.qxDecrypt(orderReturnTypeMapper.getAdminNameById(applyDto.getApplyUserId())));
			log.setCreateBy(applyDto.getApplyUserId()+StringUtils.EMPTY);
			log.setUpdateBy(applyDto.getApplyUserId()+StringUtils.EMPTY);
		}
		log.setCreateTime(new Date());
		log.setUpdateTime(new Date());
		orderReturnProcessLogMapper.save(log);
		
		OrderReturnDto returnDto = new OrderReturnDto();
		returnDto.setReturnNo(header.getReturnNo());
		result.getResponseBody().setData(returnDto);
		
		return result;
	}
	
	/**
	 * 判断退货skuCode是否错误
	 * @param applyDto
	 * @return
	 */
	private String isSkuCodeError(ReturnApplyDto applyDto){
		String errorMsg = StringUtils.EMPTY;
		// 存放订单所有商品明细
		Map<String,Integer> orderItemMap = new HashMap<String,Integer>();
		List<OrderItemDto> itemList = orderReturnTypeMapper.getOrderItemByOrderId(applyDto.getOrderId());
		for(OrderItemDto item : itemList){
			if(item.getReturnAttribute() == 1){
				if(orderItemMap.get(item.getSkuCode())!=null){
					orderItemMap.put(item.getSkuCode(), orderItemMap.get(item.getSkuCode())+item.getQty());
				}else{
					orderItemMap.put(item.getSkuCode(), item.getQty());
				}
			}
		}
		for(OrderItemDto orderItem : applyDto.getDetails()){
			if(orderItemMap.get(orderItem.getSkuCode()) == null){
				return orderItem.getSkuCode();
			}
		}
		return errorMsg;
	}

	

	/**
	 * 判断是否超出订单商品数多次退货
	 * 
	 * <p>如果超出，返回true</p>
	 * 
	 * @param applyDto
	 * @return
	 */
	private boolean isOverReturn(ReturnApplyDto applyDto) {
		// 存放订单所有商品明细
		Map<String,Integer> orderItemMap = new HashMap<String,Integer>();
		// 存放退货的所有商品明细（包含已经退货和本次退货商品）
		Map<String,Integer> reutrnItemMap = new HashMap<String,Integer>();
		
		//原始订单的商品明细
		List<OrderItemDto> itemList = orderReturnTypeMapper.getOrderItemByOrderId(applyDto.getOrderId());
		for(OrderItemDto item : itemList){
			if(item.getReturnAttribute() == 1){
				if(orderItemMap.get(item.getSkuCode())!=null){
					orderItemMap.put(item.getSkuCode(), orderItemMap.get(item.getSkuCode())+item.getQty());
				}else{
					orderItemMap.put(item.getSkuCode(), item.getQty());
				}
			}
		}
		//已经申请退货的商品明细
		List<OrderItemDto> detailList = orderReturnDetailMapper.getOrderReturnItemByOrderId(applyDto.getOrderId());
		Iterator<OrderItemDto> iterator = detailList.iterator();
		while (iterator.hasNext()) {
			OrderItemDto detail = iterator.next();
			if(StringUtils.isBlank(detail.getSkuCode())){//不存在退货商品
				iterator.remove();continue;
			}
			
			if(reutrnItemMap.get(detail.getSkuCode())!=null){
				reutrnItemMap.put(detail.getSkuCode(), reutrnItemMap.get(detail.getSkuCode())+detail.getQty());
			}else{
				reutrnItemMap.put(detail.getSkuCode(), detail.getQty());
			}
		}
		
		//加上本次退货商品
		if(CollectionUtils.isNotEmpty(applyDto.getDetails())){
			for(OrderItemDto dto : applyDto.getDetails()){
				if(reutrnItemMap.get(dto.getSkuCode())!=null){
					reutrnItemMap.put(dto.getSkuCode(), reutrnItemMap.get(dto.getSkuCode())+dto.getApplyQty());
				}else{
					reutrnItemMap.put(dto.getSkuCode(), dto.getApplyQty());
				}
			}
		}
		for(Map.Entry<String,Integer> returnEntry : reutrnItemMap.entrySet()){
			for(Map.Entry<String,Integer> itemEntry : orderItemMap.entrySet()){
				if(itemEntry.getKey().equals(returnEntry.getKey()) && returnEntry.getValue()>itemEntry.getValue()){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断订单可以退货商品是否已经全部申请退货
	 * 
	 * 如果全部已经退货
	 * 修改ve_deal_order表after_sale=5（售后操作 1.已退款 2.已退货 3.已退款,已退货 0.正常结单 5.订单所有商品已申请退货）
	 * 
	 * @param applyDto
	 */
	@Deprecated
	private void updateDealOrderAfterSale(ReturnApplyDto applyDto) {
		//原始订单的商品明细
		List<OrderItemDto> itemList = orderReturnTypeMapper.getOrderItemByOrderId(applyDto.getOrderId());
		//已经申请退货的商品明细
		List<OrderItemDto> detailList = orderReturnDetailMapper.getOrderReturnItemByOrderId(applyDto.getOrderId());
		for(OrderItemDto item : itemList){
			//支持14天无理由退货的商品、减去已经申请退货数量
			if(CollectionUtils.isNotEmpty(detailList)){
				for(OrderItemDto detail : detailList){
					if(item.getReturnAttribute()==1 && detail.getSkuCode().equals(item.getSkuCode())){
						item.setQty(item.getQty() - detail.getQty());
					}
				}
			}
			//减去本次退货商品
			if(CollectionUtils.isNotEmpty(applyDto.getDetails())){
				for(OrderItemDto detail : applyDto.getDetails()){
					if(item.getReturnAttribute()==1 && detail.getSkuCode().equals(item.getSkuCode())){
						item.setQty(item.getQty() - detail.getQty());
					}
				}
			}
		}
		boolean allSkuReturn = true;
		for(OrderItemDto item : itemList){
			if(item.getReturnAttribute()==1 && item.getQty()>0){
				allSkuReturn = false;
				break;
			}
		}
		if(allSkuReturn){
			orderReturnTypeMapper.updateDealOrderAllReturned(applyDto.getOrderId());
		}
	}


	@Override
	public ResultEntity<OrderReturnDto> getReturnOrderList(Map<String, Object> params) {
		ResultEntity<OrderReturnDto> result = new ResultEntity<OrderReturnDto>();
		
		List<OrderReturnHeader> headers = orderReturnHeaderMapper.getOrderReturnListPage(params);
		if(headers.isEmpty()){
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("查询客退申请单失败,客退申请单不存在,userId:"+((OrderReturnHeaderQuery)params.get("query")).getUserId());
			return result;
		}
		
		List<OrderReturnDto> returnList = new ArrayList<OrderReturnDto>();
		for(OrderReturnHeader header : headers){
			OrderReturnDto dto = new OrderReturnDto();
			List<OrderReturnDetail> details = orderReturnDetailMapper.getByOrderReturnHeaderId(header.getId());
			if(CollectionUtils.isNotEmpty(details)){
				List<OrderItemDto> itemList = new ArrayList<OrderItemDto>();
				for(OrderReturnDetail detail : details){
					OrderItemDto item = new OrderItemDto();
					item.setQty(detail.getQty());
					item.setSkuName(detail.getSkuName());
					item.setImgUrl(StringUtils.EMPTY);
					String imageUrl = orderReturnTypeMapper.getImageUrlBySkuCode(header.getOrderId(), detail.getSkuCode());
					if(StringUtils.isNotBlank(imageUrl)){
						item.setImgUrl(IMAGE_PRE_HTTP_URL+imageUrl.substring(1));
					}
					item.setGoodsId(orderReturnDetailMapper.getDealIdBySkuCode(detail.getSkuCode()));
					itemList.add(item);
				}
				dto.setDetails(itemList);
			}
			dto.setApplyTime(DateFormatUtil.formatDateTime(header.getCreateTime()));
			dto.setOrderSn(header.getOrderSn());
			dto.setReturnNo(header.getReturnNo());
			dto.setStatus(header.getStatus());
			returnList.add(dto);
		}
		
		result.getResponseBody().setDatas(returnList);
		result.setQuery(((OrderReturnHeaderQuery)params.get("query")));
		
		return result;
	}
	
	@Override
	public ResultEntity<OrderReturnDto> getReturnOrderDetail(String returnNo) {
		ResultEntity<OrderReturnDto> result = new ResultEntity<OrderReturnDto>();
		OrderReturnHeader header = orderReturnHeaderMapper.getOrderReturnHeaderByReturnNo(returnNo);
		if(header == null){
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("查询客退申请单明细失败,客退申请单不存在,returnNo:"+returnNo);
			return result;
		}
		
		OrderReturnDto dto = new OrderReturnDto();
		//退货单明细
		List<OrderReturnDetail> details = orderReturnDetailMapper.getByOrderReturnHeaderId(header.getId());
		if(CollectionUtils.isNotEmpty(details)){
			List<OrderItemDto> itemList = new ArrayList<OrderItemDto>();
			for(OrderReturnDetail detail : details){
				OrderItemDto item = new OrderItemDto();
				item.setUnitPrice(detail.getUnitPrice());
				item.setQty(detail.getQty());
				item.setSkuName(detail.getSkuName());
				item.setImgUrl(StringUtils.EMPTY);
				item.setReasonCode(detail.getReturnReason());
				String imageUrl = orderReturnTypeMapper.getImageUrlBySkuCode(header.getOrderId(), detail.getSkuCode());
				if(StringUtils.isNotBlank(imageUrl)){
					item.setImgUrl(IMAGE_PRE_HTTP_URL+imageUrl.substring(1));
				}
				item.setGoodsId(orderReturnDetailMapper.getDealIdBySkuCode(detail.getSkuCode()));
				itemList.add(item);
			}
			dto.setDetails(itemList);
		}
		dto.setOrderSn(header.getOrderSn());
		dto.setReturnNo(header.getReturnNo());
		dto.setRefund(header.getReturnMoney());
		dto.setStatus(header.getStatus());
		dto.setApplyTime(DateFormatUtil.formatDateTime(header.getCreateTime()));
		
		//退货处理日志
		List<OrderReturnProcessLog> list = orderReturnProcessLogMapper.getLogsByReturnNo(returnNo);
		if(CollectionUtils.isNotEmpty(list)){
			List<ReturnProcessLogDto> dtoList = new ArrayList<ReturnProcessLogDto>();
			for(OrderReturnProcessLog log : list){
				ReturnProcessLogDto logDto = new ReturnProcessLogDto();
				logDto.setProcessMsg(log.getStatusMsg());
				logDto.setProcessTime(DateFormatUtil.formatDateTime(log.getCreateTime()));
				logDto.setReturnNo(returnNo);
				dtoList.add(logDto);
			}
			dto.setProcessLogs(dtoList);
		}
		result.getResponseBody().setData(dto);
		
		return result;
	}


	@Override
	public ResultEntity<ReturnProcessLogDto> getProcessLogs(String returnNo) {
		ResultEntity<ReturnProcessLogDto> result = new ResultEntity<ReturnProcessLogDto>();
		
		List<ReturnProcessLogDto> dtoList = new ArrayList<ReturnProcessLogDto>();
		List<OrderReturnProcessLog> list = orderReturnProcessLogMapper.getLogsByReturnNo(returnNo);
		if(CollectionUtils.isNotEmpty(list)){
			for(OrderReturnProcessLog log : list){
				ReturnProcessLogDto dto = new ReturnProcessLogDto();
				dto.setProcessMsg(log.getStatusMsg());
				dto.setProcessTime(DateFormatUtil.formatDateTime(log.getCreateTime()));
				dto.setReturnNo(log.getReturnNo());
				dto.setProcessUser(log.getCreateBy());
				dtoList.add(dto);
			}
		}
		result.getResponseBody().setDatas(dtoList);
		return result;
	}


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED , isolation= Isolation.READ_COMMITTED ,rollbackFor = { Exception.class })
	public ResultEntity<Object> updateTransportNo(ExpressInfoDto expressInfo) {
		ResultEntity<Object> result = new ResultEntity<Object>();
		
		OrderReturnHeader entity = orderReturnHeaderMapper.getOrderReturnHeaderByReturnNo(expressInfo.getReturnNo());
		if(entity == null || StringUtils.isBlank(expressInfo.getReturnNo())){
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("填写物流信息失败,客退申请单不存在,returnNo:"+expressInfo.getReturnNo());
			return result;
		}
		String status=entity.getStatus();
		String statusBms = entity.getStatusBms();
		entity.setStatusAndMsg(OrderReturnHeader.STATUS_RECEIVEING, expressInfo.getExpressName(), expressInfo.getTransportNo(), 0);
		//更新客退单状态，如果为非新建状态 则不需要更新状态
		boolean flag  = true;
		if(!OrderReturnHeader.STATUS_INIT.equals(status)){
			//不需要更新客退单状态
			flag = false;
			entity.setStatus(status);
			entity.setStatusBms(statusBms);
		}
		entity.setTransportNo(expressInfo.getTransportNo());
		entity.setExpressCode(expressInfo.getExpressCode());
		entity.setExpressName(expressInfo.getExpressName());
		entity.setUpdateTime(new Date());
		orderReturnHeaderMapper.update(entity);
		
		OrderReturnProcessLog log = new OrderReturnProcessLog();
		log.setReturnNo(expressInfo.getReturnNo());
		log.setStatusAndMsg(OrderReturnHeader.STATUS_RECEIVEING, expressInfo.getExpressName(), expressInfo.getTransportNo(), 0);
		if(!flag){
			log.setStatus(status);
			log.setStatusBms(statusBms);
		}
		//会员填写物流信息
		if(expressInfo.getCreateUserType() == 1 ){
			log.setProcessUser(UDFQxDecrypt.qxDecrypt(orderReturnTypeMapper.getUserNameById(expressInfo.getCreateUserId())));
			log.setCreateBy(expressInfo.getCreateUserId()+StringUtils.EMPTY);
			log.setUpdateBy(expressInfo.getCreateUserId()+StringUtils.EMPTY);
			//客服填写物流信息	
		}else{
			log.setProcessUser(UDFQxDecrypt.qxDecrypt(orderReturnTypeMapper.getAdminNameById(expressInfo.getCreateUserId())));
			log.setCreateBy(expressInfo.getCreateUserId()+StringUtils.EMPTY);
			log.setUpdateBy(expressInfo.getCreateUserId()+StringUtils.EMPTY);
		}
		
		log.setCreateTime(new Date());
		log.setUpdateTime(new Date());
		orderReturnProcessLogMapper.save(log);
		
		return result;
	}


	@Override
	public ResultEntity<ExpressInfoDto> getExpressAllList() {
		ResultEntity<ExpressInfoDto> result = new ResultEntity<ExpressInfoDto>();
		
		List<ExpressInfoDto> dtoList = new ArrayList<ExpressInfoDto>(); 
		List<VeExpress> list = veExpressMapper.getSimpleExpressAll();
		if(CollectionUtils.isNotEmpty(list)){
			for(VeExpress express : list){
				ExpressInfoDto dto = new ExpressInfoDto();
				dto.setExpressCode(express.getClassName());
				dto.setExpressName(express.getName());
				dtoList.add(dto);
			}
		}
		result.getResponseBody().setDatas(dtoList);
		
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED , isolation= Isolation.READ_COMMITTED ,rollbackFor = { Exception.class })
	public ResultEntity<Object> updateCancelOrder(String returnNo) {
		ResultEntity<Object> result = new ResultEntity<Object>();
		
		OrderReturnHeader header = orderReturnHeaderMapper.getOrderReturnHeaderByReturnNo(returnNo);
		if(header == null){
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("取消退货失败,客退申请单不存在,returnNo:"+returnNo);
			return result;
		}
		if(StringUtils.isNotBlank(header.getStatus()) && Integer.valueOf(header.getStatus())>100){
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("取消退货失败,退货已经发出，不允许取消");
			return result;
		}
		header.setStatusAndMsg(OrderReturnHeader.STATUS_CANCEL, null, null, 0);
		header.setUpdateTime(new Date());
		header.setUpdateBy(header.getUserId()+StringUtils.EMPTY);
		orderReturnHeaderMapper.update(header);
		
		OrderReturnProcessLog log = new OrderReturnProcessLog();
		log.setReturnNo(returnNo);
		log.setStatusAndMsg(OrderReturnHeader.STATUS_CANCEL, null, null, 0);
		log.setProcessUser(UDFQxDecrypt.qxDecrypt(orderReturnTypeMapper.getUserNameById(header.getUserId())));
		log.setCreateBy(header.getUserId()+StringUtils.EMPTY);
		log.setUpdateBy(header.getUserId()+StringUtils.EMPTY);
		log.setCreateTime(new Date());
		log.setUpdateTime(new Date());
		orderReturnProcessLogMapper.save(log);
		
		return result;
	}
	
	
	@Override
	public ResultEntity<RefundCalculateDto> getRefundCalculate(ReturnApplyDto applyDto) {
		ResultEntity<RefundCalculateDto> result = new ResultEntity<RefundCalculateDto>();
		
		
		return result;
	}



	
	
}

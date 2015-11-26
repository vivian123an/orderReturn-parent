package me.quxiu.orderReturn.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.quxiu.orderReturn.base.impl.BaseServiceImpl;
import me.quxiu.orderReturn.dto.OrderItemDto;
import me.quxiu.orderReturn.dto.OrderReturnHeaderDetailDto;
import me.quxiu.orderReturn.dto.OrderReturnHeaderDto;
import me.quxiu.orderReturn.dto.OrderReturnProcessRemarkDto;
import me.quxiu.orderReturn.dto.ReturnProcessLogDto;
import me.quxiu.orderReturn.mapper.OrderReturnDetailMapper;
import me.quxiu.orderReturn.mapper.OrderReturnHeaderMapper;
import me.quxiu.orderReturn.mapper.OrderReturnProcessLogMapper;
import me.quxiu.orderReturn.mapper.OrderReturnProcessRemarkMapper;
import me.quxiu.orderReturn.mapper.OrderReturnTypeMapper;
import me.quxiu.orderReturn.model.OrderReturnDetail;
import me.quxiu.orderReturn.model.OrderReturnHeader;
import me.quxiu.orderReturn.model.OrderReturnProcessLog;
import me.quxiu.orderReturn.model.OrderReturnProcessRemark;
import me.quxiu.orderReturn.query.OrderReturnHeaderQuery;
import me.quxiu.orderReturn.service.OrderReturnHeaderService;
import me.quxiu.orderReturn.service.OrderReturnProcessService;
import me.quxiu.orderReturn.util.DateFormatUtil;
import me.quxiu.orderReturn.util.UDFQxDecrypt;
import me.quxiu.orderReturn.util.UDFQxEncrypt;
import me.quxiu.share.result.Reason;
import me.quxiu.share.result.ResultEntity;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 保存客户的客退申请单
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年10月19日 下午5:33:18
 * 
 */

@Service("orderReturnHeaderService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class OrderReturnHeaderServiceImpl extends BaseServiceImpl<OrderReturnHeader> implements OrderReturnHeaderService{

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OrderReturnHeaderMapper orderReturnHeaderMapper;
	@Autowired
	private OrderReturnDetailMapper orderReturnDetailMapper;
	
	@Autowired
	private OrderReturnTypeMapper orderReturnTypeMapper;
	
	@Autowired
	private OrderReturnProcessService orderReturnProcessService;
	
	
	@Autowired
	OrderReturnProcessRemarkMapper orderReturnProcessRemarkMapper;
	
	@Autowired
	OrderReturnProcessLogMapper orderReturnProcessLogMapper;
	
	@Override
	protected void initMapper() {
		mapper = orderReturnHeaderMapper;
	}

	@Override
	public ResultEntity<OrderReturnHeader> getOrderReturnHeaderPage(Map<String, Object> params) {
		ResultEntity<OrderReturnHeader> result = new ResultEntity<OrderReturnHeader>();
		
		List<OrderReturnHeader> headers = orderReturnHeaderMapper.getOrderReturnListPage(params);
		if(headers.isEmpty()){
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("查询客退申请单失败,客退申请单不存在,userId:"+params.get("userId"));
			return result;
		}
		
		for(OrderReturnHeader header: headers){
			List<OrderReturnDetail> details = orderReturnDetailMapper.getByOrderReturnHeaderId(header.getId());
			header.setDetails(details);
		}
		result.getResponseBody().setDatas(headers);
		result.setQuery(((OrderReturnHeaderQuery)params.get("query")));
		
		return result;
	}
	
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED , isolation= Isolation.READ_COMMITTED ,rollbackFor = { Exception.class })
	public ResultEntity<Object> saveOrderReturn(OrderReturnHeader header) {
		
		ResultEntity<Object> result = new ResultEntity<Object>();
		
		int orderCount = orderReturnHeaderMapper.getOrderCountByOrderId(header.getOrderId());
		if(orderCount == 0){
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("客退申请失败,原始订单不存在,orderId:"+header.getOrderId());
			return result;
		}
		
		int orderCount2 = orderReturnHeaderMapper.getOrderCountByOrderSn(header.getOrderSn());
		if(orderCount2 == 0){
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("客退申请失败,原始订单不存在,orderSn:"+header.getOrderSn());
			return result;
		}
		
		int userCount = orderReturnHeaderMapper.getUserCountByUserId(header.getUserId());
		if(userCount == 0){
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("客退申请失败,用户不存在,userId:"+header.getUserId());
			return result;
		}
		
		header.setReturnNo(String.valueOf(System.currentTimeMillis()));
		header.setStatus(OrderReturnHeader.STATUS_INIT);
		header.setIsSend(0);
		header.setSendTime(null);
		header.setSendTimes(0);
		header.setIsDelete(false);
		header.setCreateTime(new Date());
		header.setUpdateTime(new Date());
		header.setCreateBy(header.getUserId()+StringUtils.EMPTY);
		
		orderReturnHeaderMapper.save(header);
		if(CollectionUtils.isNotEmpty(header.getDetails())){
			for(OrderReturnDetail detail : header.getDetails()){
				detail.setOrderReturnHeaderId(header.getId());
				detail.setIsDelete(false);
				detail.setCreateTime(new Date());
				detail.setUpdateTime(new Date());
				detail.setCreateBy(header.getUserId()+StringUtils.EMPTY);
				orderReturnDetailMapper.save(detail);
			}
		}
		
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED , isolation= Isolation.READ_COMMITTED ,rollbackFor = { Exception.class })
	public ResultEntity<Object> updateTransportNo(Integer headerId,
			String transportNo) {
		ResultEntity<Object> result = new ResultEntity<Object>();
		
		if(headerId == null || headerId==0){
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("客退申请失败,orderReturnId空");
			return result;
		}
		
		if(StringUtils.isBlank(transportNo)){
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("客退申请失败,transportNo空");
			return result;
		}
		
		OrderReturnHeader entity = orderReturnHeaderMapper.get(headerId);
		if(entity == null){
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("客退申请失败,客退申请单不存在,orderReturnId:"+headerId);
			return result;
		}
		
		entity.setTransportNo(transportNo);
		entity.setUpdateTime(new Date());
		orderReturnHeaderMapper.update(entity);
		
		return result;
	}



	/**
	 * BMS后台 客退单列表查询
	 */
	@Override
	public ResultEntity<OrderReturnHeaderDto> getOrderReturnHeaderBmsPage(
			Map<String, Object> params) {
		
		ResultEntity<OrderReturnHeaderDto> result = new ResultEntity<OrderReturnHeaderDto>();
		OrderReturnHeaderQuery query = (OrderReturnHeaderQuery)params.get("query");
//		String contactPhoneNo = query.getContactPhoneNo();
//		if(StringUtils.isNotBlank(contactPhoneNo)){
//			query.setContactPhoneNo(UDFQxEncrypt.qxEncrypt(contactPhoneNo));
//		}
		result.setQuery(query);
		//查询总数
		Integer allNum = orderReturnHeaderMapper.queryAllRows(params);
		List<OrderReturnHeaderDto> headers  = new ArrayList<OrderReturnHeaderDto>();
		if(allNum<=0){
			result.getResponseBody().setDatas(headers);
			return result;
		}
		query.setTotalRecord(allNum);
		int pageIndex = query.getPageIndex()<=0?1:query.getPageIndex();
		params.put("startIndex", (pageIndex-1)*query.getPageSize());
		params.put("pageSize", query.getPageSize());
		headers = orderReturnHeaderMapper.getOrderReturnListPageForBms(params);
		if(headers!=null&&headers.size()>0){
			for(OrderReturnHeaderDto dto:headers){
//				dto.setContactPhoneNo(StringUtils.isNotBlank(dto.getContactPhoneNo())?UDFQxDecrypt.qxDecrypt(dto.getContactPhoneNo()):StringUtils.EMPTY);
				dto.setUserAccount(StringUtils.isNotBlank(dto.getUserAccount())?UDFQxDecrypt.qxDecrypt(dto.getUserAccount()):StringUtils.EMPTY);
//				dto.setContactUser(dto.getUserAccount());
			}
		}
		result.getResponseBody().setDatas(headers);
		
		
		return result;
	}
	
	/**
	 * BMS后台详情查询
	 * 
	 */
	public ResultEntity<OrderReturnHeaderDetailDto> getOrderReturnDetailForBms(Long orderReturnHeaderId){
		
		ResultEntity<OrderReturnHeaderDetailDto> result  = new ResultEntity<OrderReturnHeaderDetailDto>();
		OrderReturnHeader header  = orderReturnHeaderMapper.get(orderReturnHeaderId);
		List<OrderItemDto> itemList  = null;
		if(header!=null){
//			double refundPrice = 0.0;
			OrderReturnHeaderDetailDto dto = new OrderReturnHeaderDetailDto();
			List<OrderReturnDetail> details = orderReturnDetailMapper.getByOrderReturnHeaderId(orderReturnHeaderId.intValue());
			if(details!=null){
			    itemList = new ArrayList<OrderItemDto>();
				for(OrderReturnDetail detail : details){
					OrderItemDto item = new OrderItemDto();
					item.setQty(detail.getQty());
					item.setSkuCode(detail.getSkuCode());
					item.setSkuName(detail.getSkuName());
					item.setGoodsId(orderReturnTypeMapper.getGoodsIdBySkuCode(detail.getSkuCode()));
					String imageUrl = orderReturnTypeMapper.getImageUrlBySkuCode(header.getOrderId(), detail.getSkuCode());
					if(StringUtils.isNotBlank(imageUrl)){
						item.setImgUrl(OrderReturnProcessServiceImpl.IMAGE_PRE_HTTP_URL+imageUrl.substring(1));
					}
					item.setReasonCode(detail.getReturnReason()==null?0:detail.getReturnReason());
					item.setUnitPrice(detail.getUnitPrice());
					if(detail.getDiscount()!=null){
						item.setDiscountPrice(detail.getDiscount().doubleValue());
					}
					item.setTotalPrice(detail.getTotalPrice());
					//支持退换属性（1-可以退换，2-已经申请退货，3-不支持退货）
					String service = orderReturnDetailMapper.findServiceByGoodsId(item.getGoodsId());
					if(StringUtils.isNotBlank(service)&&service.contains("4")){
						item.setReturnAttribute(1);
					}else{
						item.setReturnAttribute(3);
					}
					itemList.add(item);
//					refundPrice+=detail.getTotalPrice();
				}
				dto.setDetails(itemList);
			}
			dto.setOrderId(header.getOrderId());
			dto.setApplyTime(DateFormatUtil.formatDateTime(header.getCreateTime()));
			dto.setOrderSn(header.getOrderSn()==null?StringUtils.EMPTY:header.getOrderSn());
			dto.setReturnNo(header.getReturnNo()==null?StringUtils.EMPTY:header.getReturnNo());
			dto.setStatus(header.getStatus());
			dto.setTransportNo(header.getTransportNo()==null?StringUtils.EMPTY:header.getTransportNo());
			//
			
			dto.setContactPhoneNo(header.getContactPhoneNo()==null?StringUtils.EMPTY:header.getContactPhoneNo());
			dto.setContactUser(header.getContactUser()==null?StringUtils.EMPTY:header.getContactUser());
			
//			dto.setContactPhoneNo(StringUtils.isNotBlank(header.getContactPhoneNo())?UDFQxDecrypt.qxDecrypt(header.getContactPhoneNo()):StringUtils.EMPTY);
//			dto.setContactUser(StringUtils.isNotBlank(header.getContactUser())?UDFQxDecrypt.qxDecrypt(header.getContactUser()):StringUtils.EMPTY);
			
			dto.setStatus(header.getStatusBms());
			dto.setRefundPrice(header.getReturnMoney()==null?0.0:header.getReturnMoney());//退款金额
			dto.setReturnReason(header.getReturnDescript()==null?StringUtils.EMPTY:header.getReturnDescript());
			//绝对路径
			String imgUrl = header.getImgUrl1();
			String dealImgUrl = StringUtils.EMPTY;
			if(StringUtils.isNotBlank(imgUrl)){
				String[] imgUrls = imgUrl.split("\\|");
				boolean flag = false;
				for(String _imgUrl:imgUrls){
					dealImgUrl = dealImgUrl+(flag?"|":StringUtils.EMPTY)+OrderReturnProcessServiceImpl.IMAGE_PRE_HTTP_URL+_imgUrl;
					flag=true;
				}
			}
			dto.setImgUrl1(dealImgUrl);
			dto.setImgUrl2(StringUtils.EMPTY);
			dto.setImgUrl3(StringUtils.EMPTY);
			dto.setExpressCode(header.getExpressCode()==null?StringUtils.EMPTY:header.getExpressCode());
			dto.setExpressName(header.getExpressName()==null?StringUtils.EMPTY:header.getExpressName());
			
			//getProcessLogs
			List<OrderReturnProcessLog> logList =  orderReturnProcessLogMapper.getLogsByReturnNoForGetProcessUser(header.getReturnNo());
			if(logList!=null&&logList.size()>0){
				List<ReturnProcessLogDto> logDtos = new ArrayList<ReturnProcessLogDto>();
				for(OrderReturnProcessLog log:logList){
					ReturnProcessLogDto logDto = new ReturnProcessLogDto();
					logDto.setProcessMsg(log.getStatusMsg());
					logDto.setProcessTime(DateFormatUtil.formatDateTime(log.getCreateTime()));
					logDto.setReturnNo(log.getReturnNo());
					logDto.setProcessUser(StringUtils.isNotBlank(log.getCreateBy())?UDFQxDecrypt.qxDecrypt(log.getCreateBy()):StringUtils.EMPTY);
					logDtos.add(logDto);
				}


				dto.setProcessLogs(logDtos);
			}
			
			//获取备注信息
			List<OrderReturnProcessRemark> remarkList = orderReturnProcessRemarkMapper.getRemarksByReturnNo(header.getReturnNo());
			List<OrderReturnProcessRemarkDto> remarkDtoList  = new ArrayList<OrderReturnProcessRemarkDto>();
			if(remarkList!=null&&remarkList.size()>0){
				for(OrderReturnProcessRemark remark:remarkList){
					OrderReturnProcessRemarkDto remarkDto = new OrderReturnProcessRemarkDto();
//					remarkDto.setProcessUser(remark.getCreateBy());//处理人
					remarkDto.setProcessUser(StringUtils.isNotBlank(remark.getCreateBy())?UDFQxDecrypt.qxDecrypt(remark.getCreateBy()):StringUtils.EMPTY);
					remarkDto.setProcessTime(DateFormatUtil.formatDateTime(remark.getCreateTime()));
					remarkDto.setRemarkMsg(remark.getRemarkMsg());
					remarkDto.setReturnNo(dto.getReturnNo());
					remarkDtoList.add(remarkDto);
				}
			}
			dto.setProcessRemarkDtos(remarkDtoList);
			result.getResponseBody().setData(dto);
		}else{
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("根据客退单ID查询不到对应的客退单");
		}
		return result;
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED , isolation= Isolation.READ_COMMITTED ,rollbackFor = { Exception.class })
	public ResultEntity<OrderReturnProcessRemarkDto> savePrcessRemark(OrderReturnProcessRemarkDto processRemarkDto){
		ResultEntity<OrderReturnProcessRemarkDto> result = new  ResultEntity<OrderReturnProcessRemarkDto>();
		if(processRemarkDto==null||StringUtils.isBlank(processRemarkDto.getProcessUser())||StringUtils.isBlank(processRemarkDto.getRemarkMsg())||
				StringUtils.isBlank(processRemarkDto.getReturnNo())){
			
			result.setReasonCode(Reason.REASON_CODE_REQUEST_ERROR);
			result.setReasonMsg("备注信息 客退单号、备注信息、处理人均不能为空");
			return result;
		}
		OrderReturnProcessRemark processRemark = new OrderReturnProcessRemark();
		processRemark.setCreateBy(processRemarkDto.getProcessUser());
		processRemark.setCreateTime(new Date());
		processRemark.setIsDelete((byte)0);
		processRemark.setRemarkMsg(processRemarkDto.getRemarkMsg());
		processRemark.setReturnNo(processRemarkDto.getReturnNo());
		processRemark.setUpdateBy(processRemarkDto.getProcessUser());
		processRemark.setUpdateTime(processRemark.getCreateTime());
		orderReturnProcessRemarkMapper.save(processRemark);
		return result;
		
	}
	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED , isolation= Isolation.READ_COMMITTED ,rollbackFor = { Exception.class })
	public ResultEntity<OrderReturnHeader> updateOrderReturnHeaderStatus(
			OrderReturnHeaderDto orderReturnHeaderDto) {
		
		ResultEntity<OrderReturnHeader> result = new  ResultEntity<OrderReturnHeader>();
		String returnNo = orderReturnHeaderDto.getReturnNo();//客退单号
		String statusBms = orderReturnHeaderDto.getStatusBms();//订单状态
		String processUser = orderReturnHeaderDto.getProcessUser();
		
		if(StringUtils.isBlank(returnNo)||StringUtils.isBlank(statusBms)){
			
			result.setReasonMsg("客退单号、订单状态均不能为空");
			result.setReasonCode(Reason.REASON_CODE_ERROR_NULLID);
			return result;
		}
		if(StringUtils.isBlank(processUser)){
			result.setReasonMsg("操作人不能为空");
			result.setReasonCode(Reason.REASON_CODE_ERROR_NULLID);
			return result;
		}
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("status", statusBms);
		params.put("statusBms", statusBms);
		params.put("returnNo", returnNo);
		params.put("processUser", processUser);
		String msg = null;
		if(OrderReturnHeader.STATUS_CANCEL.equals(statusBms)){
			msg = OrderReturnHeader.STATUS_CANCEL_MSG;
			
		}else if(OrderReturnHeader.STATUS_REFUNDING.equals(statusBms)){
			msg =  OrderReturnHeader.STATUS_REFUNDING_MSG;
		}
		params.put("statusMsg", msg);
		try {
			int updateResult = orderReturnHeaderMapper.updateRefundStatusByReturnNo(params);
			if(updateResult!=1){
				result.setReasonMsg("更新客退单状态失败,根据客退单号查询不到对应的记录");
				result.setReasonCode(Reason.REASON_CODE_ERROR_NULLID);
				return result;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setReasonMsg("更新客退单状态失败："+e.getMessage());
			result.setReasonCode(Reason.REASON_CODE_ERROR_NULLID);
			return result;
			
		}
		//添加日志
		OrderReturnProcessLog processLog = new OrderReturnProcessLog();
		processLog.setCreateBy(processUser);
		processLog.setCreateTime(new Date());
		processLog.setProcessUser(processUser);
		processLog.setReturnNo(returnNo);
		processLog.setStatus(statusBms);
		processLog.setStatusBms(statusBms);
		processLog.setStatusMsg(msg);
		orderReturnProcessLogMapper.save(processLog);
		
		return result;
	}

	
}

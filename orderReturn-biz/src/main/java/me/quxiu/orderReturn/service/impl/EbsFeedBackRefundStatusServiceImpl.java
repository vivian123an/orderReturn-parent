package me.quxiu.orderReturn.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.quxiu.orderReturn.inf.dto.EbsRefundStatusDto;
import me.quxiu.orderReturn.inf.dto.EbsRefundStatusItemDto;
import me.quxiu.orderReturn.inf.dto.EbsRefundStatusResponseDto;
import me.quxiu.orderReturn.inf.dto.EbsRefundStatusResponseItemDto;
import me.quxiu.orderReturn.mapper.InfEbsOrderRefundMapper;
import me.quxiu.orderReturn.mapper.OrderReturnHeaderMapper;
import me.quxiu.orderReturn.mapper.OrderReturnProcessLogMapper;
import me.quxiu.orderReturn.model.InfEbsOrderRefund;
import me.quxiu.orderReturn.model.OrderReturnHeader;
import me.quxiu.orderReturn.model.OrderReturnProcessLog;
import me.quxiu.orderReturn.service.EbsFeedBackRefundStatusService;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.StaxDriver;


@Service("ebsFeedBackRefundStatusService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class EbsFeedBackRefundStatusServiceImpl implements
		EbsFeedBackRefundStatusService {

	
	private static Log log = LogFactory.getLog(EbsFeedBackRefundStatusServiceImpl.class);
	
	@Autowired
	InfEbsOrderRefundMapper infEbsOrderRefundMapper;
	
	@Autowired
	OrderReturnHeaderMapper orderReturnHeaderMapper;
	
	

	@Autowired
	OrderReturnProcessLogMapper orderReturnProcessLogMapper;
	
	
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED , isolation= Isolation.READ_COMMITTED ,rollbackFor = { Exception.class })
	public String updateReFundStatus(String xmlStr) {
		
		if(StringUtils.isBlank(xmlStr)){
			log.error("传入报文为空");
			return returnErrorMessage("传入报文为空");
		}
		 XStream xs = new XStream(new StaxDriver(new NoNameCoder()));
	    xs.alias("root", EbsRefundStatusDto.class);
        xs.alias("item", EbsRefundStatusItemDto.class);
        xs.addImplicitCollection(EbsRefundStatusDto.class, "ebsRefundStatusItemDtos");
        EbsRefundStatusDto ebsRefundStatusDto=null;
        try {
        	  ebsRefundStatusDto = (EbsRefundStatusDto)xs.fromXML(xmlStr);
		} catch (Exception e) {
			log.error("传入报文格式有误,xmlStr:"+xmlStr);
			e.printStackTrace();
			return returnErrorMessage("传入报文格式有误");
		}
        //更新退款单状态
        if(ebsRefundStatusDto!=null&&ebsRefundStatusDto.getEbsRefundStatusItemDtos()!=null&&ebsRefundStatusDto.getEbsRefundStatusItemDtos().size()>0){
        	List<EbsRefundStatusItemDto> dtos = ebsRefundStatusDto.getEbsRefundStatusItemDtos();
        	//返回对象
        	EbsRefundStatusResponseDto responseDto = new EbsRefundStatusResponseDto();
        	List<EbsRefundStatusResponseItemDto> responseItemDtos = new ArrayList<EbsRefundStatusResponseItemDto>();
        	responseDto.setEbsRefundStatusResponseItemDtos(responseItemDtos);
        	for(EbsRefundStatusItemDto dto:dtos){
        		EbsRefundStatusResponseItemDto responseItemDto = new EbsRefundStatusResponseItemDto();
        		responseItemDtos.add(responseItemDto);
        		Long refundId = dto.getRefund_id();
        		responseItemDto.setId(dto.getId());
        		responseItemDto.setError(true);
        		if(refundId==null||refundId.longValue()<=0){
        			responseItemDto.setMessage("退款单ID不存在");
        			continue;
        		}
        		InfEbsOrderRefund infEbsOrderRefund = infEbsOrderRefundMapper.get(refundId);
        		if(infEbsOrderRefund==null){
        			responseItemDto.setMessage("退款单ID不存在");
        			continue;
        		}
        		//更新 退款申请表 退款状态
        		Map<String,Object> params = new HashMap<String,Object>();
        		params.put("id", refundId);
        		params.put("refund_date", dto.getRefund_date());
        		params.put("status", InfEbsOrderRefund.STATUS_REFUND_FINISH);//已退款
        		try {
        			infEbsOrderRefundMapper.updateOrderRefundStatus(params);
				} catch (Exception e) {
					log.error(e.getMessage());
					responseItemDto.setMessage("更新退款单状态失败");
        			continue;
				}
        		//更新客退单表状态
        		Map<String,Object> refundParams = new HashMap<String,Object>();
        		refundParams.put("status", OrderReturnHeader.STATUS_REFUND_FINISH);//已退款
        		refundParams.put("statusBms",  OrderReturnHeader.STATUS_REFUND_FINISH);//已退款
        		refundParams.put("statusMsg", OrderReturnHeader.STATUS_REFUND_FINISH_MSG);
        		refundParams.put("id",dto.getOrder_return_header_id());
        		try {
        			orderReturnHeaderMapper.updateRefundStatus(refundParams);
				} catch (Exception e) {
					log.error(e.getMessage());
					responseItemDto.setMessage("更新客退单状态失败");
        			continue;
				}
        		
        		//新增一条记录 order_return_process_log
        		//保存退货日志
				OrderReturnProcessLog processLog = new OrderReturnProcessLog();
				processLog.setCreateBy("SYS");
				processLog.setCreateTime(new Date());
				processLog.setProcessUser("SYS");
				OrderReturnHeader orh = orderReturnHeaderMapper.get(dto.getOrder_return_header_id());
				processLog.setReturnNo(orh!=null?orh.getReturnNo():StringUtils.EMPTY);//客退单号
				processLog.setStatus(OrderReturnHeader.STATUS_REFUND_FINISH);
				processLog.setStatusBms(OrderReturnHeader.STATUS_REFUND_FINISH);
				processLog.setStatusMsg(OrderReturnHeader.STATUS_REFUND_FINISH_MSG);
				orderReturnProcessLogMapper.save(processLog);
        		
        		
        		//更新订单状态为已退款 
        		Map<String,Object> orderParams = new HashMap<String,Object>();
        		orderParams.put("order_id", infEbsOrderRefund.getOrderId());
        		//售后操作 1.已退款 2.已退货 3.已退款,已退货 0.正常结单
        		orderParams.put("order_status",1);
        		try {
        			int result = infEbsOrderRefundMapper.updateOrderAfterSaleById(orderParams);
        			if(result!=1){
        				responseItemDto.setMessage("订单ID不存在");
            			continue;
        			}
				} catch (Exception e) {
					log.error(e.getMessage());
					responseItemDto.setMessage("更新订单状态失败");
        			continue;
				}
        		responseItemDto.setError(false);
        	}
        	XStream returnXs = new XStream(new StaxDriver(new NoNameCoder()));
        	returnXs.alias("root", EbsRefundStatusResponseDto.class);
        	returnXs.alias("item", EbsRefundStatusResponseItemDto.class);
        	returnXs.addImplicitCollection(EbsRefundStatusResponseDto.class, "ebsRefundStatusResponseItemDtos");
        	return returnXs.toXML(responseDto);
        }else{
        	return returnErrorMessage("报文没有具体内容");
        }
	}
	
	
	private String returnErrorMessage(String message){
		
		EbsRefundStatusResponseDto responseDto = new EbsRefundStatusResponseDto();
    	List<EbsRefundStatusResponseItemDto> responseItemDtos = new ArrayList<EbsRefundStatusResponseItemDto>();
    	responseDto.setEbsRefundStatusResponseItemDtos(responseItemDtos);
    	EbsRefundStatusResponseItemDto responseItemDto = new EbsRefundStatusResponseItemDto();
    	responseItemDto.setMessage(message);
    	responseItemDto.setError(true);
		responseItemDtos.add(responseItemDto);
		
		XStream returnXs = new XStream(new StaxDriver(new NoNameCoder()));
    	returnXs.alias("root", EbsRefundStatusResponseDto.class);
    	returnXs.alias("item", EbsRefundStatusResponseItemDto.class);
    	returnXs.addImplicitCollection(EbsRefundStatusResponseDto.class, "ebsRefundStatusResponseItemDtos");
    	return returnXs.toXML(responseDto);
	}

}

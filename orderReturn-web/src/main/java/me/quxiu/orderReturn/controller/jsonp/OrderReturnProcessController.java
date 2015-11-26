package me.quxiu.orderReturn.controller.jsonp;

import java.util.HashMap;
import java.util.Map;

import me.quxiu.orderReturn.dto.ExpressInfoDto;
import me.quxiu.orderReturn.dto.OrderItemDto;
import me.quxiu.orderReturn.dto.OrderReturnDto;
import me.quxiu.orderReturn.dto.ReturnApplyDto;
import me.quxiu.orderReturn.dto.ReturnProcessLogDto;
import me.quxiu.orderReturn.model.OrderReturnType;
import me.quxiu.orderReturn.query.OrderReturnHeaderQuery;
import me.quxiu.orderReturn.service.OrderReturnProcessService;
import me.quxiu.share.result.Reason;
import me.quxiu.share.result.ResultEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年11月9日 下午2:08:50
 * 
 */

@Controller
@RequestMapping
@ResponseBody
public class OrderReturnProcessController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OrderReturnProcessService orderReturnProcessService;
	
	/**
	 * 订单明细列表
	 * @param orderId
	 * @return
	 */
	@RequestMapping("orderReturn/orderItemList/{orderId}")
	public ResultEntity<OrderItemDto> getOrderItemList(@PathVariable Integer orderId){
		ResultEntity<OrderItemDto> result = new ResultEntity<OrderItemDto>();
		try {
			return orderReturnProcessService.getOrderItemList(orderId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setReasonCode(Reason.REASON_CODE_SERVER_ERROR);
			result.setReasonMsg("服务器内部错误,orderId:"+orderId);
			return result;
		}
	}
	
	/**
	 * 退货原因列表
	 * @return
	 */
	@RequestMapping("orderReturn/returnReasonList")
	public ResultEntity<OrderReturnType> getReturnReasonList(){
		ResultEntity<OrderReturnType> result = new ResultEntity<OrderReturnType>();
		try {
			return orderReturnProcessService.getReturnReasonList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setReasonCode(Reason.REASON_CODE_SERVER_ERROR);
			result.setReasonMsg("服务器内部错误");
			return result;
		}
	}
	
	/**
	 * 退货单提交
	 * @param applyDto
	 * @return
	 */
	@RequestMapping("orderReturn/apply")	
	public ResultEntity<OrderReturnDto> saveReturnApply(@RequestBody ReturnApplyDto applyDto){
		ResultEntity<OrderReturnDto> result = new ResultEntity<OrderReturnDto>();
		try {
			return orderReturnProcessService.saveReturnApply(applyDto);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setReasonCode(Reason.REASON_CODE_SERVER_ERROR);
			result.setReasonMsg("服务器内部错误，orderId："+applyDto.getOrderId());
			return result;
		}
	}
	
	/**
	 * 我的售后单列表
	 * @param params
	 * @return
	 */
	@RequestMapping("orderReturn/customerService/returnList")
	public ResultEntity<OrderReturnDto> getReturnOrderList(OrderReturnHeaderQuery queryDto){
		ResultEntity<OrderReturnDto> result = new ResultEntity<OrderReturnDto>();
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("query", queryDto);
			return orderReturnProcessService.getReturnOrderList(params);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setReasonCode(Reason.REASON_CODE_SERVER_ERROR);
			result.setReasonMsg("服务器内部错误");
			return result;
		}
	}
	
	/**
	 * 客退单明细
	 * @param returnNo
	 * @return
	 */
	@RequestMapping("orderReturn/customerService/returnDetail/{returnNo}")
	public ResultEntity<OrderReturnDto> getReturnOrderDetail(@PathVariable("returnNo") String returnNo){
		ResultEntity<OrderReturnDto> result = new ResultEntity<OrderReturnDto>();
		try {
			return orderReturnProcessService.getReturnOrderDetail(returnNo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setReasonCode(Reason.REASON_CODE_SERVER_ERROR);
			result.setReasonMsg("服务器内部错误");
			return result;
		}
	}
	
	/**
	 * 客退单处理系统日记
	 * @param returnNo
	 * @return
	 */
	@RequestMapping("orderReturn/customerService/statusList/{returnNo}")	
	public ResultEntity<ReturnProcessLogDto> getProcessLogs(@PathVariable("returnNo") String returnNo){
		ResultEntity<ReturnProcessLogDto> result = new ResultEntity<ReturnProcessLogDto>();
		try {
			return orderReturnProcessService.getProcessLogs(returnNo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setReasonCode(Reason.REASON_CODE_SERVER_ERROR);
			result.setReasonMsg("服务器内部错误，returnNo："+returnNo);
			return result;
		}
	}
	
	/**
	 * 填写物流信息
	 * @param expressInfo
	 * @return
	 */
	@RequestMapping("orderReturn/customerService/updateExpress")	
	public ResultEntity<Object> updateTransportNo(ExpressInfoDto expressInfo){
		ResultEntity<Object> result = new ResultEntity<Object>();
		try {
			return orderReturnProcessService.updateTransportNo(expressInfo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setReasonCode(Reason.REASON_CODE_SERVER_ERROR);
			result.setReasonMsg("服务器内部错误");
			return result;
		}
	}
	
	/**
	 * 取快递公司列表
	 * @return
	 */
	@RequestMapping("orderReturn/customerService/expressList")
	public ResultEntity<ExpressInfoDto> getExpressAllList(){
		ResultEntity<ExpressInfoDto> result = new ResultEntity<ExpressInfoDto>();
		try {
			return orderReturnProcessService.getExpressAllList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setReasonCode(Reason.REASON_CODE_SERVER_ERROR);
			result.setReasonMsg("服务器内部错误");
			return result;
		}
	}
	
	/**
	 * 取消客退单
	 * @param returnNo
	 * @return
	 */
	@RequestMapping("orderReturn/customerService/cancelReturn/{returnNo}")
	public ResultEntity<Object> updateCancelOrder(@PathVariable String returnNo){
		ResultEntity<Object> result = new ResultEntity<Object>();
		try {
			return orderReturnProcessService.updateCancelOrder(returnNo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setReasonCode(Reason.REASON_CODE_SERVER_ERROR);
			result.setReasonMsg("服务器内部错误");
			return result;
		}
	}
}

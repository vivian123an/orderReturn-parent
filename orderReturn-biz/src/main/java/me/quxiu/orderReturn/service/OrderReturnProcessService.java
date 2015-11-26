package me.quxiu.orderReturn.service;

import java.util.Map;

import me.quxiu.orderReturn.dto.ExpressInfoDto;
import me.quxiu.orderReturn.dto.OrderItemDto;
import me.quxiu.orderReturn.dto.OrderReturnDto;
import me.quxiu.orderReturn.dto.RefundCalculateDto;
import me.quxiu.orderReturn.dto.ReturnApplyDto;
import me.quxiu.orderReturn.dto.ReturnProcessLogDto;
import me.quxiu.orderReturn.model.OrderReturnType;
import me.quxiu.share.result.ResultEntity;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年11月5日 上午11:25:46
 * 
 */

public interface OrderReturnProcessService {

	/**
	 * 订单明细列表
	 * @param orderId
	 * @return
	 */
	public ResultEntity<OrderItemDto> getOrderItemList(Integer orderId);
	
	/**
	 * 退货原因列表
	 * @return
	 */
	public ResultEntity<OrderReturnType> getReturnReasonList();
	
	/**
	 * 退货单提交
	 * @param applyDto
	 * @return
	 */
	public ResultEntity<OrderReturnDto> saveReturnApply(ReturnApplyDto applyDto);
	
	/**
	 * 我的售后单列表
	 * @param params
	 * @return
	 */
	public ResultEntity<OrderReturnDto> getReturnOrderList(Map<String,Object> params);
	
	/**
	 * 客退单明细
	 * @param returnNo
	 * @return
	 */
	public ResultEntity<OrderReturnDto> getReturnOrderDetail(String returnNo);
	
	/**
	 * 客退单处理系统日记
	 * @param returnNo
	 * @return
	 */
	public ResultEntity<ReturnProcessLogDto> getProcessLogs(String returnNo);
	
	/**
	 * 填写物流信息
	 * @param expressInfo
	 * @return
	 */
	public ResultEntity<Object> updateTransportNo(ExpressInfoDto expressInfo);
	
	/**
	 * 取快递公司列表
	 * @return
	 */
	public ResultEntity<ExpressInfoDto> getExpressAllList();
	
	/**
	 * 计算退货退款金额
	 * @return
	 */
	public ResultEntity<RefundCalculateDto> getRefundCalculate(ReturnApplyDto applyDto);
	
	/**
	 * 取消退货
	 * @param returnNo
	 * @return
	 */
	public ResultEntity<Object> updateCancelOrder(String returnNo);
}

package me.quxiu.orderReturn.mapper;

import java.util.List;
import java.util.Map;

import me.quxiu.orderReturn.base.BaseMapper;
import me.quxiu.orderReturn.model.InfEbsOrderRefund;

import org.apache.ibatis.annotations.Param;

public interface InfEbsOrderRefundMapper  extends BaseMapper<InfEbsOrderRefund>{
	
	
	/**
	 * 更新退款单状态为成功退款
	 * @param params
	 * @return
	 */
	public int updateOrderRefundStatus(Map<String,Object> params);
	
	/**
	 * 更新订单afterSale状态为已退款
	 * @param orderId
	 * @return
	 */
	public int updateOrderAfterSaleById(Map<String,Object> params);
	
	/**
	 * 
	 * @param startIndex
	 * @param num
	 * @return
	 */
	public List<InfEbsOrderRefund> getUnSendOrderRefundsBySendTimes(@Param("sendTimes")int sendTimes,@Param("maxCount")int maxCount);
	
	/**
	 * 更新状态为已发送
	 * @param refundIds
	 * @return
	 */
	public int updateSendStatus(List<Long> refundIds);
	
	/**
	 * 更新没有成功发送的信息
	 * @param refundId
	 * @param errMsg
	 * @return
	 */
	public int updateSendTimesAdd(Map<String,Object> params);

}

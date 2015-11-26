package me.quxiu.orderReturn.mapper;

import java.util.List;
import java.util.Map;

import me.quxiu.orderReturn.base.BaseMapper;
import me.quxiu.orderReturn.dto.OrderReturnHeaderDto;
import me.quxiu.orderReturn.model.OrderReturnHeader;

import org.apache.ibatis.annotations.Param;

public interface OrderReturnHeaderMapper extends BaseMapper<OrderReturnHeader>{

	/**
	 * 查询用户的客退申请单
	 * @param userId
	 * @return
	 */
	public List<OrderReturnHeader> getOrderReturnListPage(Map<String, Object> params);
	
	/**
	 * 查询用户的客退申请单BMS后台
	 * @param userId
	 * @return
	 */
	public List<OrderReturnHeaderDto> getOrderReturnListPageForBms(Map<String, Object> params);
	
	
	public int queryAllRows(Map<String, Object> params);
	
	/**
	 * 根据客退单号查询客退单
	 * @param returnNo
	 * @return
	 */
	public OrderReturnHeader getOrderReturnHeaderByReturnNo(String returnNo);
	
	/**
	 * 根据订单Id,检查订单是否存在
	 * @param orderId
	 * @return
	 */
	public int getOrderCountByOrderId(int orderId);
	
	/**
	 * 根据订单号,检查订单是否存在
	 * @param orderSn
	 * @return
	 */
	public int getOrderCountByOrderSn(String orderSn);
	
	/**
	 * 根据用户Id,检查用户是否存在
	 * @param userId
	 * @return
	 */
	public int getUserCountByUserId(int userId);
	
	/**
	 * 根据发送次数获取未发送成功的记录
	 * @param sendTimes
	 * @param maxCount
	 * @return
	 */
	public List<OrderReturnHeader> getUnSendHeadersBySendTimes(@Param("sendTimes")int sendTimes,@Param("maxCount")int maxCount);
	
	/**
	 * 修改发送成功标志
	 * @param headerIds
	 * @return
	 */
	public int updateSendStatus(@Param("headerIds") List<String> headerIds);
	
	/**
	 * 递增发送次数
	 * @param headerIds
	 * @return
	 */
	public int updateSendTimesAdd(@Param("headerIds") List<Integer> headerIds);
	
	/**
	 * 更新退款状态
	 * @param params
	 * @return
	 */
	public int updateRefundStatus(Map<String,Object> params);
	
	/**
	 * 更新退款状态
	 * @param params
	 * @return
	 */
	public int updateRefundStatusByReturnNo(Map<String,Object> params);
	
	
}
package me.quxiu.orderReturn.mapper;

import java.util.List;

import me.quxiu.orderReturn.base.BaseMapper;
import me.quxiu.orderReturn.model.OrderReturnProcessLog;

public interface OrderReturnProcessLogMapper extends BaseMapper<OrderReturnProcessLog>{

	/**
	 * 根据客退单号查询客退处理日志
	 * @param returnNo
	 * @return
	 */
	public List<OrderReturnProcessLog> getLogsByReturnNo(String returnNo);
	
	/**
	 * 根据客退单号查询客退处理日志
	 * @param returnNo
	 * @return
	 */
	public List<OrderReturnProcessLog> getLogsByReturnNoForGetProcessUser(String returnNo);
	
}
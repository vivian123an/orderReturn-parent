package me.quxiu.orderReturn.mapper;

import java.util.List;

import me.quxiu.orderReturn.base.BaseMapper;
import me.quxiu.orderReturn.model.OrderReturnProcessRemark;


public interface OrderReturnProcessRemarkMapper extends BaseMapper<OrderReturnProcessRemark>{

	
	/**
	 * 根据客退单号查询客退处理日志
	 * @param returnNo
	 * @return
	 */
	public List<OrderReturnProcessRemark> getRemarksByReturnNo(String returnNo);
}
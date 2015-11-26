package me.quxiu.orderReturn.service;

import java.util.Map;

import me.quxiu.orderReturn.base.BaseService;
import me.quxiu.orderReturn.dto.OrderReturnHeaderDetailDto;
import me.quxiu.orderReturn.dto.OrderReturnHeaderDto;
import me.quxiu.orderReturn.dto.OrderReturnProcessRemarkDto;
import me.quxiu.orderReturn.model.OrderReturnHeader;
import me.quxiu.share.result.ResultEntity;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年10月19日 下午5:32:21
 * 
 */

public interface OrderReturnHeaderService extends BaseService<OrderReturnHeader>{

	public ResultEntity<Object> saveOrderReturn(OrderReturnHeader orderReturnHeader);
	
	public ResultEntity<Object> updateTransportNo(Integer headerId,String transportNo);
	
	/**
	 * 分页查询
	 * @param userId
	 * @return
	 */
	public ResultEntity<OrderReturnHeader> getOrderReturnHeaderPage(Map<String, Object> params);
	
	
	/**
	 * 客退单BMS后台查询
	 * @param params
	 * @return
	 */
	public ResultEntity<OrderReturnHeaderDto> getOrderReturnHeaderBmsPage(Map<String,Object> params);
	
	
	
	public ResultEntity<OrderReturnHeaderDetailDto> getOrderReturnDetailForBms(Long orderReturnHeaderId);
	
	
	/**
	 * 更新客退单状态
	 * @param OrderReturnHeaderDto
	 * @return
	 */
	public ResultEntity<OrderReturnHeader> updateOrderReturnHeaderStatus(OrderReturnHeaderDto orderReturnHeaderDto) ;
	
	
	public ResultEntity<OrderReturnProcessRemarkDto> savePrcessRemark(OrderReturnProcessRemarkDto processRemarkDto);
}

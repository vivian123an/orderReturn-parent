package me.quxiu.orderReturn.mapper;

import java.util.List;

import me.quxiu.orderReturn.base.BaseMapper;
import me.quxiu.orderReturn.dto.OrderItemDto;
import me.quxiu.orderReturn.model.OrderReturnDetail;

public interface OrderReturnDetailMapper extends BaseMapper<OrderReturnDetail>{

	public List<OrderReturnDetail> getByOrderReturnHeaderId(Integer headerId);
	
	public List<OrderItemDto> getOrderReturnItemByOrderId(Integer orderId);
	
	public Integer getDealIdBySkuCode(String skuCode);
	
	/**
	 * 查询退货属性
	 * @param cateId
	 * @return
	 */
	public String findServiceByGoodsId(Integer goodsId);
}
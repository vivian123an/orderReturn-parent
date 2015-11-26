package me.quxiu.orderReturn.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.quxiu.orderReturn.base.BaseMapper;
import me.quxiu.orderReturn.dto.OrderItemDto;
import me.quxiu.orderReturn.model.OrderReturnType;

public interface OrderReturnTypeMapper extends BaseMapper<OrderReturnType>{

	public List<OrderReturnType> getAll();
	
	public List<OrderItemDto> getOrderItemByOrderId(int orderId);
	
	public String getPoNumberByOrderIdAndSkuCode(@Param("orderId")int orderId, @Param("skuCode") String skuCode);
	
	public HashMap<String, Object> getSupplierIdAndDiscount(@Param("orderId")int orderId, @Param("skuCode") String skuCode);
	
	public String getImageUrlBySkuCode(@Param("orderId")int orderId, @Param("skuCode") String skuCode);
	
	/**
	 * 更新订单售后状态、可退货商品已全部退货
	 * @param orderId
	 */
	public void updateDealOrderAllReturned(Integer orderId);
	
	public Integer getGoodsIdBySkuCode(@Param("skuCode") String skuCode);
	
	/**
	 * 根据会员Id取会员帐号
	 * @param userId
	 * @return
	 */
	public String getUserNameById(@Param("userId") Integer userId);
	
	/**
	 * 根据客服Id取客服名称
	 * @param adminId
	 * @return
	 */
	public String getAdminNameById(@Param("adminId") Integer adminId);
}
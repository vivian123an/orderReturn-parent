package me.quxiu.orderReturn.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 提交客退申请DTO
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年11月5日 下午3:56:19
 * 
 */

public class ReturnApplyDto implements Serializable{

	private static final long serialVersionUID = 2131903924907600250L;
	
	/**
	 * 退款总金额
	 */
	private Double refundTotal;
	private String imgUrl1;
	private String imgUrl2;
	private String imgUrl3;
	/**
	 * 退货商品明细
	 */
	private List<OrderItemDto> details;
	/**
	 * 订单号
	 */
	private String orderSn;
	/**
	 * 订单ID
	 */
	private int orderId;
	/**
	 * 退货原因描述
	 */
	private String returnDescript;
	/**
	 * 用户Id
	 */
	private int userId;
	
	/**
	 * 申请退货数量
	 */
	private int applyQty;
	
	/**
	 * 客退单申请人Id
	 */
	private int applyUserId;
	
	/**
	 * 退货商品明细
	 * @return details
	 */
	public List<OrderItemDto> getDetails() {
		return details;
	}
	/**
	 * 退货商品明细
	 * @param details
	 */
	public void setDetails(List<OrderItemDto> details) {
		this.details = details;
	}
	/**
	 * imgUrl1
	 * @return imgUrl1
	 */
	public String getImgUrl1() {
		return imgUrl1;
	}
	/**
	 * imgUrl1
	 * @param imgUrl1
	 */
	public void setImgUrl1(String imgUrl1) {
		this.imgUrl1 = imgUrl1;
	}
	/**
	 * imgUrl2
	 * @return imgUrl2
	 */
	public String getImgUrl2() {
		return imgUrl2;
	}
	/**
	 * imgUrl2
	 * @param imgUrl2
	 */
	public void setImgUrl2(String imgUrl2) {
		this.imgUrl2 = imgUrl2;
	}
	/**
	 * imgUrl3
	 * @return imgUrl3
	 */
	public String getImgUrl3() {
		return imgUrl3;
	}
	/**
	 * imgUrl3
	 * @param imgUrl3
	 */
	public void setImgUrl3(String imgUrl3) {
		this.imgUrl3 = imgUrl3;
	}
	/**
	 * 订单号
	 * @return orderSn
	 */
	public String getOrderSn() {
		return orderSn;
	}
	/**
	 * 订单号
	 * @param orderSn
	 */
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	/**
	 * 订单ID
	 * @return orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * 订单ID
	 * @param orderId
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * 退货原因描述
	 * @return returnDescript
	 */
	public String getReturnDescript() {
		return returnDescript;
	}
	/**
	 * 退货原因描述
	 * @param returnDescript
	 */
	public void setReturnDescript(String returnDescript) {
		this.returnDescript = returnDescript;
	}
	/**
	 * 用户Id
	 * @return userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * 用户Id
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * 退款总金额
	 * @return refundTotal
	 */
	public Double getRefundTotal() {
		return refundTotal;
	}
	/**
	 * 退款总金额
	 * @param refundTotal
	 */
	public void setRefundTotal(Double refundTotal) {
		this.refundTotal = refundTotal;
	}
	/**
	 * 申请退货数量
	 * @return applyQty
	 */
	public int getApplyQty() {
		return applyQty;
	}
	/**
	 * 申请退货数量
	 * @param applyQty
	 */
	public void setApplyQty(int applyQty) {
		this.applyQty = applyQty;
	}
	/**
	 * 客退单申请人Id
	 * @return applyUserId
	 */
	public int getApplyUserId() {
		return applyUserId;
	}
	/**
	 * 客退单申请人Id
	 * @param applyUserId
	 */
	public void setApplyUserId(int applyUserId) {
		this.applyUserId = applyUserId;
	}
	
	
	
}

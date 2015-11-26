package me.quxiu.orderReturn.dto;

import java.io.Serializable;

/**
 * 客退申请，退款总额Dto
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年11月10日 下午3:25:40
 * 
 */

public class RefundCalculateDto implements Serializable{
	
	private static final long serialVersionUID = 7673960856675551226L;
	/**
	 * 优惠金额
	 */
	private double discount;
	/**
	 * 退款金额
	 */
	private double refund;
	/**
	 * 订单总金额
	 */
	private double totalPrice;
	
	/**
	 * 订购运费
	 */
	private double expressPrice;
	
	/**
	 * 回寄邮费补贴
	 */
	private double returnExpressPrice;
	
	/**
	 * 商品数量
	 */
	private int itemNumber;
	/**
	 * 商品小计
	 */
	private double subtotal;
	
	
	
	
	
	
	
	/**
	 * 优惠金额
	 * @return discount
	 */
	public double getDiscount() {
		return discount;
	}
	/**
	 * 优惠金额
	 * @param discount
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	/**
	 * 退款金额
	 * @return refund
	 */
	public double getRefund() {
		return refund;
	}
	/**
	 * 退款金额
	 * @param refund
	 */
	public void setRefund(double refund) {
		this.refund = refund;
	}
	/**
	 * 订单总金额
	 * @return totalPrice
	 */
	public double getTotalPrice() {
		return totalPrice;
	}
	/**
	 * 订单总金额
	 * @param totalPrice
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * 订购运费
	 * @return expressPrice
	 */
	public double getExpressPrice() {
		return expressPrice;
	}
	/**
	 * 订购运费
	 * @param expressPrice
	 */
	public void setExpressPrice(double expressPrice) {
		this.expressPrice = expressPrice;
	}
	/**
	 * 回寄邮费补贴
	 * @return returnExpressPrice
	 */
	public double getReturnExpressPrice() {
		return returnExpressPrice;
	}
	/**
	 * 回寄邮费补贴
	 * @param returnExpressPrice
	 */
	public void setReturnExpressPrice(double returnExpressPrice) {
		this.returnExpressPrice = returnExpressPrice;
	}
	/**
	 * 商品数量
	 * @return itemNumber
	 */
	public int getItemNumber() {
		return itemNumber;
	}
	/**
	 * 商品数量
	 * @param itemNumber
	 */
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	/**
	 * 商品小计
	 * @return subtotal
	 */
	public double getSubtotal() {
		return subtotal;
	}
	/**
	 * 商品小计
	 * @param subtotal
	 */
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	
	
	
	
	
	
	
}

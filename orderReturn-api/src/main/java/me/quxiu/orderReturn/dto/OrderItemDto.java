package me.quxiu.orderReturn.dto;

import java.io.Serializable;

/**
 * 
 * 订单明细（包含可以退货商品、已经申请退货商品、不支持14天退货商品）
 * 
 *  returnAttribute 支持退换属性（1-可以退换，2-已经申请退货，3-不支持退货）
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年11月5日 上午11:08:11
 * 
 */

public class OrderItemDto implements Serializable{

	private static final long serialVersionUID = -8974273668646829532L;
	
	/**
	 * 商品图片
	 */
	private String imgUrl;
	/**
	 * 申请退货数量
	 */
	private int applyQty;
	/**
	 * 商品数量
	 */
	private int qty;
	/**
	 * 商品编号
	 */
	private String skuCode;
	/**
	 * 商品Id
	 */
	private Integer goodsId;
	/**
	 * 商品名称
	 */
	private String skuName;
	/**
	 * 支持退换属性（1-可以退换，2-已经申请退货，3-不支持退货）
	 */
	private int returnAttribute;
	/**
	 * 总价小计
	 */
	private double totalPrice;
	/**
	 * 单价
	 */
	private double unitPrice;
	/**
	 * 折扣
	 */
	private double discountPrice;
	
	/**
	 * 退货原因编号
	 */
	private int reasonCode;
	
	
	/**
	 * 商品图片
	 * @return imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * 商品图片
	 * @param imgUrl
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * 商品数量
	 * @return qty
	 */
	public int getQty() {
		return qty;
	}
	/**
	 * 商品数量
	 * @param qty
	 */
	public void setQty(int qty) {
		this.qty = qty;
	}
	/**
	 * 商品编号
	 * @return skuCode
	 */
	public String getSkuCode() {
		return skuCode;
	}
	/**
	 * 商品编号
	 * @param skuCode
	 */
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode == null ? null : skuCode.trim();
	}
	/**
	 * 商品名称
	 * @return skuName
	 */
	public String getSkuName() {
		return skuName;
	}
	/**
	 * 商品名称
	 * @param skuName
	 */
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	/**
	 * 支持退换属性（1-可以退换，2-已经申请退货，3-不支持退货）
	 * @return returnAttribute
	 */
	public int getReturnAttribute() {
		return returnAttribute;
	}
	/**
	 * 支持退换属性（1-可以退换，2-已经申请退货，3-不支持退货）
	 * @param returnAttribute
	 */
	public void setReturnAttribute(int returnAttribute) {
		this.returnAttribute = returnAttribute;
	}
	/**
	 * 总价小计
	 * @return totalPrice
	 */
	public double getTotalPrice() {
		return totalPrice;
	}
	/**
	 * 总价小计
	 * @param totalPrice
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * 单价
	 * @return unitPrice
	 */
	public double getUnitPrice() {
		return unitPrice;
	}
	/**
	 * 单价
	 * @param unitPrice
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * 折扣
	 * @return discountPrice
	 */
	public double getDiscountPrice() {
		return discountPrice;
	}
	/**
	 * 折扣
	 * @param discountPrice
	 */
	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}
	/**
	 * 退货原因编号
	 * @return reasonCode
	 */
	public int getReasonCode() {
		return reasonCode;
	}
	/**
	 * 退货原因编号
	 * @param reasonCode
	 */
	public void setReasonCode(int reasonCode) {
		this.reasonCode = reasonCode;
	}
	/**
	 * 商品Id
	 * @return goodsId
	 */
	public Integer getGoodsId() {
		return goodsId;
	}
	/**
	 * 商品Id
	 * @param goodsId
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
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
	
	
}

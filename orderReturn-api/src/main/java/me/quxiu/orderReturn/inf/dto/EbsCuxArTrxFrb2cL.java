package me.quxiu.orderReturn.inf.dto;

import java.io.Serializable;

/**
 * 
 * 对应于EBS系统，销售订单行表
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年10月23日 下午2:56:48
 * 
 */

public class EbsCuxArTrxFrb2cL implements Serializable{
	
	private static final long serialVersionUID = -4455804643921637745L;
	/**
	 * 事务处理ID
	 */
	private Integer TRX_ID;
	/**
	 * 销售订单号
	 */
	private String ORDER_NUM;
	/**
	 * 销售订单ID号
	 */
	private Integer ORDER_ID;
	/**
	 * 订单行号
	 */
	private Integer LINE_NUM;
	/**
	 * 摘要（代收直发供应商货款、供应商购买积分，取消订单退款）
	 */
	private String DESCRIPTION;
	/**
	 * 数量
	 */
	private Integer QTY;
	/**
	 * 单价
	 */
	private Integer PRICE;
	/**
	 * 金额
	 */
	private Integer AMOUNT;
	/**
	 * 项目
	 */
	private String ITEM_NO;
	/**
	 * SKU名称
	 */
	private String SKU_NAME;
	/**
	 * SKU数量
	 */
	private Integer SKU_QTY;
	/**
	 * SKU单价
	 */
	private Integer SKU_PRICE;
	/**
	 * 供应商ID
	 */
	private Integer SUPPLIER_ID;
	/**
	 * PO号
	 */
	private String PO_NUMBER;
	/**
	 * 扣点
	 */
	private Integer DISCOUNT;
	/**
	 * SKU编码
	 */
	private String SKU_CODE;
	
	
	public Integer getTRX_ID() {
		return TRX_ID;
	}
	public void setTRX_ID(Integer tRX_ID) {
		TRX_ID = tRX_ID;
	}
	public String getORDER_NUM() {
		return ORDER_NUM;
	}
	public void setORDER_NUM(String oRDER_NUM) {
		ORDER_NUM = oRDER_NUM;
	}
	public Integer getORDER_ID() {
		return ORDER_ID;
	}
	public void setORDER_ID(Integer oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}
	public Integer getLINE_NUM() {
		return LINE_NUM;
	}
	public void setLINE_NUM(Integer lINE_NUM) {
		LINE_NUM = lINE_NUM;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public Integer getQTY() {
		return QTY;
	}
	public void setQTY(Integer qTY) {
		QTY = qTY;
	}
	public Integer getPRICE() {
		return PRICE;
	}
	public void setPRICE(Integer pRICE) {
		PRICE = pRICE;
	}
	public Integer getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(Integer aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public String getITEM_NO() {
		return ITEM_NO;
	}
	public void setITEM_NO(String iTEM_NO) {
		ITEM_NO = iTEM_NO;
	}
	public String getSKU_NAME() {
		return SKU_NAME;
	}
	public void setSKU_NAME(String sKU_NAME) {
		SKU_NAME = sKU_NAME;
	}
	public Integer getSKU_QTY() {
		return SKU_QTY;
	}
	public void setSKU_QTY(Integer sKU_QTY) {
		SKU_QTY = sKU_QTY;
	}
	public Integer getSKU_PRICE() {
		return SKU_PRICE;
	}
	public void setSKU_PRICE(Integer sKU_PRICE) {
		SKU_PRICE = sKU_PRICE;
	}
	public Integer getSUPPLIER_ID() {
		return SUPPLIER_ID;
	}
	public void setSUPPLIER_ID(Integer sUPPLIER_ID) {
		SUPPLIER_ID = sUPPLIER_ID;
	}
	public String getPO_NUMBER() {
		return PO_NUMBER;
	}
	public void setPO_NUMBER(String pO_NUMBER) {
		PO_NUMBER = pO_NUMBER;
	}
	public Integer getDISCOUNT() {
		return DISCOUNT;
	}
	public void setDISCOUNT(Integer dISCOUNT) {
		DISCOUNT = dISCOUNT;
	}
	public String getSKU_CODE() {
		return SKU_CODE;
	}
	public void setSKU_CODE(String sKU_CODE) {
		SKU_CODE = sKU_CODE;
	}
	
	


	
	
	
}

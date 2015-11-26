package me.quxiu.orderReturn.inf.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 对应于EBS系统、销售订单头表:
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年10月23日 下午2:55:47
 * 
 */

public class EbsCuxArTrxFrb2cH implements Serializable{
	
	private static final long serialVersionUID = -1042080075260887582L;
	
	private List<EbsCuxArTrxFrb2cL> LINES;
	
	/**
	 * 事务处理ID
	 */
	private String TRX_ID;
	/**
	 * 业务实体ID
	 */
	private Integer ORG_ID;
	/**
	 * 业务类型（代收货款、代收货款退货、取消订单发票）
	 */
	private String BUSINESS_TYPE;
	/**
	 * 销售订单号
	 */
	private String ORDER_NUM;
	/**
	 * 销售订单ID号
	 */
	private Integer ORDER_ID;
	/**
	 * 事物处理日期（取发货日期、退货日期、取消日期）
	 */
	private String TRX_DATE;
	/**
	 * 总账日期（取发货日期、退货日期、取消日期）
	 */
	private String GL_DATE;
	/**
	 * 支付方式（支付宝,微信）
	 */
	private String PAY_METHOD;
	/**
	 * 支付类型（线上,线下）
	 */
	private String PAY_TYPE;
	/**
	 * 总金额
	 */
	private Integer AMOUNT;
	/**
	 * 支付金额
	 */
	private Integer NET_AMOUNT;
	
	
	public List<EbsCuxArTrxFrb2cL> getLINES() {
		return LINES;
	}
	public void setLINES(List<EbsCuxArTrxFrb2cL> lINES) {
		LINES = lINES;
	}
	public String getTRX_ID() {
		return TRX_ID;
	}
	public void setTRX_ID(String tRX_ID) {
		TRX_ID = tRX_ID;
	}
	public Integer getORG_ID() {
		return ORG_ID;
	}
	public void setORG_ID(Integer oRG_ID) {
		ORG_ID = oRG_ID;
	}
	public String getBUSINESS_TYPE() {
		return BUSINESS_TYPE;
	}
	public void setBUSINESS_TYPE(String bUSINESS_TYPE) {
		BUSINESS_TYPE = bUSINESS_TYPE;
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
	public String getTRX_DATE() {
		return TRX_DATE;
	}
	public void setTRX_DATE(String tRX_DATE) {
		TRX_DATE = tRX_DATE;
	}
	public String getGL_DATE() {
		return GL_DATE;
	}
	public void setGL_DATE(String gL_DATE) {
		GL_DATE = gL_DATE;
	}
	public String getPAY_METHOD() {
		return PAY_METHOD;
	}
	public void setPAY_METHOD(String pAY_METHOD) {
		PAY_METHOD = pAY_METHOD;
	}
	public String getPAY_TYPE() {
		return PAY_TYPE;
	}
	public void setPAY_TYPE(String pAY_TYPE) {
		PAY_TYPE = pAY_TYPE;
	}
	public Integer getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(Integer aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public Integer getNET_AMOUNT() {
		return NET_AMOUNT;
	}
	public void setNET_AMOUNT(Integer nET_AMOUNT) {
		NET_AMOUNT = nET_AMOUNT;
	}
	
	
	
	
	
	
	
}

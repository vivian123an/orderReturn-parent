package me.quxiu.orderReturn.inf.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 退款申请发送给EBS报文对应的实体对象
 * @author wangmin
 *
 */
public class EbsCuxArRecFrb2cItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3663361930459246040L;
	
	private Long TRX_ID;
	
	private String OU_NAME;//实物所属公司
	
	private String ORDER_NUM;
	
	private Long ORDER_ID;
	
	private String ORDER_ATTACH_NUM;//销售订单子单号
	
	private Date REC_DATE;//客服生成客退申请时间
	
	private Date GL_DATE; //GL日期 （退款日期）
	
	private String BUSINESS_TYPE;//
	
	private Integer SUPPLIER_ID;
	
	private String SUPPLIER_NAME;
	
	private String PAY_METHOD;
	
	private String PAY_TYPE;
	
	private String SHIPPER;
	
	private String STATUS;
	
	private String TYPE;
	
	private Double AMOUNT;
	
	private Double DISCOUNT_AMOUNT;
	
	private Double NET_AMOUNT;
	
	private String LINE_DESC;
	
	private String RECEIPT_METHOD;
	
	
	

	public Long getTRX_ID() {
		return TRX_ID;
	}

	public void setTRX_ID(Long tRX_ID) {
		TRX_ID = tRX_ID;
	}

	public String getOU_NAME() {
		return OU_NAME;
	}

	public void setOU_NAME(String oU_NAME) {
		OU_NAME = oU_NAME;
	}

	public String getORDER_NUM() {
		return ORDER_NUM;
	}

	public void setORDER_NUM(String oRDER_NUM) {
		ORDER_NUM = oRDER_NUM;
	}

	public Long getORDER_ID() {
		return ORDER_ID;
	}

	public void setORDER_ID(Long oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}

	public String getORDER_ATTACH_NUM() {
		return ORDER_ATTACH_NUM;
	}

	public void setORDER_ATTACH_NUM(String oRDER_ATTACH_NUM) {
		ORDER_ATTACH_NUM = oRDER_ATTACH_NUM;
	}

	public Date getREC_DATE() {
		return REC_DATE;
	}

	public void setREC_DATE(Date rEC_DATE) {
		REC_DATE = rEC_DATE;
	}

	public Date getGL_DATE() {
		return GL_DATE;
	}

	public void setGL_DATE(Date gL_DATE) {
		GL_DATE = gL_DATE;
	}

	public String getBUSINESS_TYPE() {
		return BUSINESS_TYPE;
	}

	public void setBUSINESS_TYPE(String bUSINESS_TYPE) {
		BUSINESS_TYPE = bUSINESS_TYPE;
	}

	public Integer getSUPPLIER_ID() {
		return SUPPLIER_ID;
	}

	public void setSUPPLIER_ID(Integer sUPPLIER_ID) {
		SUPPLIER_ID = sUPPLIER_ID;
	}

	public String getSUPPLIER_NAME() {
		return SUPPLIER_NAME;
	}

	public void setSUPPLIER_NAME(String sUPPLIER_NAME) {
		SUPPLIER_NAME = sUPPLIER_NAME;
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

	public String getSHIPPER() {
		return SHIPPER;
	}

	public void setSHIPPER(String sHIPPER) {
		SHIPPER = sHIPPER;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public Double getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(Double aMOUNT) {
		AMOUNT = aMOUNT;
	}

	public Double getDISCOUNT_AMOUNT() {
		return DISCOUNT_AMOUNT;
	}

	public void setDISCOUNT_AMOUNT(Double dISCOUNT_AMOUNT) {
		DISCOUNT_AMOUNT = dISCOUNT_AMOUNT;
	}

	public Double getNET_AMOUNT() {
		return NET_AMOUNT;
	}

	public void setNET_AMOUNT(Double nET_AMOUNT) {
		NET_AMOUNT = nET_AMOUNT;
	}

	public String getLINE_DESC() {
		return LINE_DESC;
	}

	public void setLINE_DESC(String lINE_DESC) {
		LINE_DESC = lINE_DESC;
	}

	public String getRECEIPT_METHOD() {
		return RECEIPT_METHOD;
	}

	public void setRECEIPT_METHOD(String rECEIPT_METHOD) {
		RECEIPT_METHOD = rECEIPT_METHOD;
	}
	
	
	
	
	

}

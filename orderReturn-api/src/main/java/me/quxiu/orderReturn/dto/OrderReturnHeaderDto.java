package me.quxiu.orderReturn.dto;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class OrderReturnHeaderDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2374878285673319891L;
	
	private Long id;
	
	private String returnNo;
	
	private String orderSn;
	
	private String userAccount;
	
	private String transportNo;
	
	private String applyTime;
	
	private String contactPhoneNo;
	
	private String contactUser;
	
	private Double  refundPrice;
	
	private String statusBms;//状态
	
	private String processUser;//处理人
	
	public OrderReturnHeaderDto(){
		returnNo = StringUtils.EMPTY; 
		orderSn = StringUtils.EMPTY; 
		userAccount = StringUtils.EMPTY; 
		transportNo = StringUtils.EMPTY; 
		contactPhoneNo = StringUtils.EMPTY; 
		contactUser = StringUtils.EMPTY; 
		statusBms = StringUtils.EMPTY; 
		applyTime =  StringUtils.EMPTY; 
		processUser =  StringUtils.EMPTY; 
	}
	
	

	public String getProcessUser() {
		return processUser;
	}



	public void setProcessUser(String processUser) {
		this.processUser = processUser;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReturnNo() {
		return returnNo;
	}

	public void setReturnNo(String returnNo) {
		this.returnNo = returnNo;
	}

	
	

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getTransportNo() {
		return transportNo;
	}

	public void setTransportNo(String transportNo) {
		this.transportNo = transportNo;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getContactPhoneNo() {
		return contactPhoneNo;
	}

	public void setContactPhoneNo(String contactPhoneNo) {
		this.contactPhoneNo = contactPhoneNo;
	}

	public String getContactUser() {
		return contactUser;
	}

	public void setContactUser(String contactUser) {
		this.contactUser = contactUser;
	}

	public Double getRefundPrice() {
		return refundPrice;
	}

	public void setRefundPrice(Double refundPrice) {
		this.refundPrice = refundPrice;
	}

	public String getStatusBms() {
		return statusBms;
	}

	public void setStatusBms(String statusBms) {
		this.statusBms = statusBms;
	}
	
	
	
	
	

}

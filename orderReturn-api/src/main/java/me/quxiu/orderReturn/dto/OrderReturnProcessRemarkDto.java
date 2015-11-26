package me.quxiu.orderReturn.dto;

import java.io.Serializable;

public class OrderReturnProcessRemarkDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 22904138277651794L;
	
	/**
	 * 客退单
	 */
	private String returnNo;
	
	/**
	 * 备注内容
	 */
	private String remarkMsg;
	
	/**
	 * 处理人
	 */
	private String processUser;
	
	/**
	 * 处理时间
	 */
	private String processTime;
	
	
	

	public String getReturnNo() {
		return returnNo;
	}

	public void setReturnNo(String returnNo) {
		this.returnNo = returnNo;
	}

	public String getRemarkMsg() {
		return remarkMsg;
	}

	public void setRemarkMsg(String remarkMsg) {
		this.remarkMsg = remarkMsg;
	}

	

	public String getProcessUser() {
		return processUser;
	}

	public void setProcessUser(String processUser) {
		this.processUser = processUser;
	}

	public String getProcessTime() {
		return processTime;
	}

	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}

	
	

}

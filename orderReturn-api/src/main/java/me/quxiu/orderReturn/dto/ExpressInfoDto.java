package me.quxiu.orderReturn.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 快递信息DTO
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年11月6日 下午4:05:15
 * 
 */

public class ExpressInfoDto implements Serializable{

	private static final long serialVersionUID = -7317306488417988969L;
	/**
	 * 快递公司编号
	 */
	private String expressCode;
	/**
	 * 快递公司名称
	 */
	private String expressName;
	/**
	 * 快递单号
	 */
	private String transportNo;	
	/**
	 * 客退单编号
	 */
	private String returnNo;
	
	/**
	 * 提交人Id
	 */
	private Integer createUserId;
	
	/**
	 * 提交人类型（1-会员，2-客服）
	 */
	private Integer createUserType;
	
	
	/**
	 * 快递公司编号
	 * @return expressCode
	 */
	public String getExpressCode() {
		return expressCode;
	}
	/**
	 * 快递公司编号
	 * @param expressCode
	 */
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	/**
	 * 快递公司名称
	 * @return expressName
	 */
	public String getExpressName() {
		return expressName;
	}
	/**
	 * 快递公司名称
	 * @param expressName
	 */
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	/**
	 * 快递单号
	 * @return transportNo
	 */
	@JsonIgnore
	public String getTransportNo() {
		return transportNo;
	}
	/**
	 * 快递单号
	 * @param transportNo
	 */
	public void setTransportNo(String transportNo) {
		this.transportNo = transportNo;
	}
	/**
	 * 客退单编号
	 * @return returnNo
	 */
	@JsonIgnore
	public String getReturnNo() {
		return returnNo;
	}
	/**
	 * 客退单编号
	 * @param returnNo
	 */
	public void setReturnNo(String returnNo) {
		this.returnNo = returnNo;
	}
	/**
	 * 提交人Id
	 * @return createUserId
	 */
	public Integer getCreateUserId() {
		return createUserId;
	}
	/**
	 * 提交人Id
	 * @param createUserId
	 */
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 提交人类型（1-会员，2-客服）
	 * @return createUserType
	 */
	public Integer getCreateUserType() {
		return createUserType;
	}
	/**
	 * 提交人类型（1-会员，2-客服）
	 * @param createUserType
	 */
	public void setCreateUserType(Integer createUserType) {
		this.createUserType = createUserType;
	}

	
}

package me.quxiu.orderReturn.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 退款申请单VO
 * 
 * @author wangmin
 *
 */
public class InfEbsOrderRefund implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2309011905036551932L;

	public final static String STATUS_INIT = "100";// 新建
	public final static String STATUS_REFUND_FINISH = "200";// 退款成功
	public final static String STATUS_REFUND_EXCEPTION = "299";// 退款异常
	public final static String STATUS_STATEMENT = "300";// 已结单

	private Long id;

	private Long orderId;
	/**
	 * 原始订单号
	 */
	private String orderSn;

	private Long userId;
	/**
	 * 退款单号
	 */
	private String refundNo;

	/**
	 * CANCEL:取消单退款申请，RETURN：退货退款申请
	 */
	private String businessType;

	/**
	 * 供应商ID
	 */
	private Integer supplierId;

	/**
	 * 支付类型
	 */
	private String payType;

	/**
	 * 支付方式
	 */
	private String payMethod;

	/**
	 * 退款金额
	 */
	private Double returnAmount;

	/**
	 * 支付金额
	 */
	private Double payAmount;

	/**
	 * 折扣金额
	 */
	private Double ecvMoney;

	private Date refundDate;

	/**
	 * 100：新建，200：已退款，299:退款异常，300：已结单
	 */
	private String status;

	/**
	 * 是否发送成功 1：是，0：否
	 */
	private Integer isSend;

	private Integer sendTimes;

	private Integer isDelete;

	private Date createTime;

	private String createBy;

	private Date updateTime;

	private String updateBy;
	/**
	 * 异常信息备注
	 */
	private String errorRemark;

	private String userDef1;
	private String userDef2;
	private String userDef3;
	private String userDef4;
	private String userDef5;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public Double getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(Double returnAmount) {
		this.returnAmount = returnAmount;
	}

	public Double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	public Double getEcvMoney() {
		return ecvMoney;
	}

	public void setEcvMoney(Double ecvMoney) {
		this.ecvMoney = ecvMoney;
	}

	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIsSend() {
		return isSend;
	}

	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}

	public Integer getSendTimes() {
		return sendTimes;
	}

	public void setSendTimes(Integer sendTimes) {
		this.sendTimes = sendTimes;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getErrorRemark() {
		return errorRemark;
	}

	public void setErrorRemark(String errorRemark) {
		this.errorRemark = errorRemark;
	}

	public String getUserDef1() {
		return userDef1;
	}

	public void setUserDef1(String userDef1) {
		this.userDef1 = userDef1;
	}

	public String getUserDef2() {
		return userDef2;
	}

	public void setUserDef2(String userDef2) {
		this.userDef2 = userDef2;
	}

	public String getUserDef3() {
		return userDef3;
	}

	public void setUserDef3(String userDef3) {
		this.userDef3 = userDef3;
	}

	public String getUserDef4() {
		return userDef4;
	}

	public void setUserDef4(String userDef4) {
		this.userDef4 = userDef4;
	}

	public String getUserDef5() {
		return userDef5;
	}

	public void setUserDef5(String userDef5) {
		this.userDef5 = userDef5;
	}

}

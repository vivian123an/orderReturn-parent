package me.quxiu.orderReturn.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import me.quxiu.share.model.BaseEntity;

/**
 * 客退申请单头
 * @author  wenan.chen@quxiu.me
 * @version 2015年10月19日 下午5:03:21
 */


public class OrderReturnHeader extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = -3540484879739437130L;
	
	/**
	 * 前端状态（100:审核通过,200:等待寄回商品,299:已取消,300:已发起退款,400:已退款）
	 * bms状态（100:审核通过,200:等待寄回商品,299:已取消,300:已发起退款,359:异常-建议退款,369:异常-建议不退款,400:已退款）
	 */
	public static final String STATUS_INIT = "100";					//审核通过
	public static final String STATUS_RECEIVEING = "200";			//等待寄回商品
	public static final String STATUS_CANCEL = "299";				//已取消
	public static final String STATUS_REFUNDING = "300";			//已经发起退款
	public static final String STATUS_REFUND_FINISH = "400";		//已退款
	
	public static final String STATUS_EXCEP_REFUND = "359";			//异常-建议退款
	public static final String STATUS_EXCEP_STOP = "369";			//异常-建议不退款
	
	public static final String STATUS_INIT_MSG = "您的退货申请已审核通过";
	public static final String STATUS_RECEIVEING_MSG = "已填写退货物流信息，快递公司：XXXX，快递单号：YYYY";
	public static final String STATUS_CANCEL_MSG = "由于（异常原因），您的退货申请已经取消。";
	public static final String STATUS_REFUNDING_MSG = "您的退款总额XXXX元，按各银行规定将于2-10个工作日到账，请留意查收。";
	public static final String STATUS_REFUND_FINISH_MSG = "您的退货申请已经完成。";
	
	public static final String STATUS_EXCEP_REFUND_MSG = "由于（异常原因），收货发生异常，仓管建议此商品退款";
	public static final String STATUS_EXCEP_STOP_MSG = "由于（异常原因），收货发生异常，仓管建议此商品不退款";
	

	/**
	 * 客退申请单明细
	 */
	private List<OrderReturnDetail> details;
	
	/**
	 * 主键
	 */
    private Integer id;
    /**
     * 客退单编号
     */
    private String returnNo;
    
    /**
     * 原始订单ID
     */
    private Integer orderId;

    /**
     * 原始订单号
     */
    private String orderSn;

    /**
     * 会员ID
     */
    private Integer userId;
    /**
     * 退货原因描述
     */
    private String returnDescript;
    /**
     * 原订单支付类型
     */
    private String payType;

    /**
     * 原订单支付方式
     */
    private String payMethod;
    
    
    /**
     * 退款金额
     */
    private Double returnMoney;
    /**
     * 快递公司编号
     */
    private String expressCode;
    /**
     * 快递公司名称
     */
    private String expressName;
    /**
     * 运单号
     */
    private String transportNo;

    /**
     * 联系人名称
     */
    private String contactUser;

    /**
     * 退货图片1
     */
    private String imgUrl1;

    /**
     * 退货图片2
     */
    private String imgUrl2;

    /**
     * 退货图片3
     */
    private String imgUrl3;

    /**
     * 收货异常原因
     */
    private String receiptReason;

    /**
     * 收货异常原因描述
     */
    private String receiptDescript;

    /**
     * 异常处理结果
     */
    private String processResult;

    /**
     * 异常处理描述
     */
    private String resultDescript;

    /**
     * 备注
     */
    private String memo;

    /**
     * 客退申请单状态（100:审核通过，200：等待寄回商品，299：已取消，300：已发起退款，400：已退款）
     */
    private String status;
    /**
     * 客退申请单状态BMS端适用（100:审核通过，200：等待寄回商品，299：已取消，300：已发起退款，359：异常-建议退款，369：异常-建议不退款，400：已退款）
     */
    private String statusBms;
    
    /**
     * 状态显示文字
     */
    private String statusMsg;
    
    /**
     * 异常处理时间
     */
    private Date errorTime;

    /**
     * 是否发送成功（1:成功，0:失败）
     */
    private Integer isSend;

    /**
     * 发送次数
     */
    private Integer sendTimes;

    /**
     * 发送成功时间
     */
    private Date sendTime;
    
    /**
     * 联系人电话
     */
    private String contactPhoneNo;

    /**
	 * 客退申请单明细
	 * @return details
	 */
	public List<OrderReturnDetail> getDetails() {
		return details;
	}

	/**
	 * 客退申请单明细
	 * @param details
	 */
	public void setDetails(List<OrderReturnDetail> details) {
		this.details = details;
	}

	/**
	 * 主键
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 原始订单ID
	 * @return orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * 原始订单ID
	 * @param orderId
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * 原始订单号
	 * @return orderSn
	 */
	public String getOrderSn() {
		return orderSn;
	}

	/**
	 * 原始订单号
	 * @param orderSn
	 */
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	/**
	 * 会员ID
	 * @return userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 会员ID
	 * @param userId
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 原订单支付类型
	 * @return payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * 原订单支付类型
	 * @param payType
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * 原订单支付方式
	 * @return payMethod
	 */
	public String getPayMethod() {
		return payMethod;
	}

	/**
	 * 原订单支付方式
	 * @param payMethod
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	/**
	 * 运单号
	 * @return transportNo
	 */
	public String getTransportNo() {
		return transportNo;
	}

	/**
	 * 运单号
	 * @param transportNo
	 */
	public void setTransportNo(String transportNo) {
		this.transportNo = transportNo;
	}

	/**
	 * 联系人名称
	 * @return contactUser
	 */
	public String getContactUser() {
		return contactUser;
	}

	/**
	 * 联系人名称
	 * @param contactUser
	 */
	public void setContactUser(String contactUser) {
		this.contactUser = contactUser;
	}

	/**
	 * 退货图片1
	 * @return imgUrl1
	 */
	public String getImgUrl1() {
		return imgUrl1;
	}

	/**
	 * 退货图片1
	 * @param imgUrl1
	 */
	public void setImgUrl1(String imgUrl1) {
		this.imgUrl1 = imgUrl1;
	}

	/**
	 * 退货图片2
	 * @return imgUrl2
	 */
	public String getImgUrl2() {
		return imgUrl2;
	}

	/**
	 * 退货图片2
	 * @param imgUrl2
	 */
	public void setImgUrl2(String imgUrl2) {
		this.imgUrl2 = imgUrl2;
	}

	/**
	 * 退货图片3
	 * @return imgUrl3
	 */
	public String getImgUrl3() {
		return imgUrl3;
	}

	/**
	 * 退货图片3
	 * @param imgUrl3
	 */
	public void setImgUrl3(String imgUrl3) {
		this.imgUrl3 = imgUrl3;
	}

	/**
	 * 收货异常原因
	 * @return receiptReason
	 */
	public String getReceiptReason() {
		return receiptReason;
	}

	/**
	 * 收货异常原因
	 * @param receiptReason
	 */
	public void setReceiptReason(String receiptReason) {
		this.receiptReason = receiptReason;
	}

	/**
	 * 收货异常原因描述
	 * @return receiptDescript
	 */
	public String getReceiptDescript() {
		return receiptDescript;
	}

	/**
	 * 收货异常原因描述
	 * @param receiptDescript
	 */
	public void setReceiptDescript(String receiptDescript) {
		this.receiptDescript = receiptDescript;
	}

	/**
	 * 异常处理结果
	 * @return processResult
	 */
	public String getProcessResult() {
		return processResult;
	}

	/**
	 * 异常处理结果
	 * @param processResult
	 */
	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	/**
	 * 异常处理描述
	 * @return resultDescript
	 */
	public String getResultDescript() {
		return resultDescript;
	}

	/**
	 * 异常处理描述
	 * @param resultDescript
	 */
	public void setResultDescript(String resultDescript) {
		this.resultDescript = resultDescript;
	}

	/**
	 * 备注
	 * @return memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 备注
	 * @param memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 客退申请单状态（100:新建，200:已发起退款，300:已退款，399:收货异常，400:已完成）
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 客退申请单状态（100:新建，200:已发起退款，300:已退款，399:收货异常，400:已完成）
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 客退申请单状态BMS端适用（100:审核通过，200：等待寄回商品，299：已取消，300：已发起退款，359：异常-建议退款，369：异常-建议不退款，400：已退款）
	 * @return statusBms
	 */
	public String getStatusBms() {
		return statusBms;
	}

	/**
	 * 客退申请单状态BMS端适用（100:审核通过，200：等待寄回商品，299：已取消，300：已发起退款，359：异常-建议退款，369：异常-建议不退款，400：已退款）
	 * @param statusBms
	 */
	public void setStatusBms(String statusBms) {
		this.statusBms = statusBms;
	}

	/**
	 * 异常处理时间
	 * @return errorTime
	 */
	public Date getErrorTime() {
		return errorTime;
	}

	/**
	 * 异常处理时间
	 * @param errorTime
	 */
	public void setErrorTime(Date errorTime) {
		this.errorTime = errorTime;
	}

	/**
	 * 是否发送成功（1:成功，0:失败）
	 * @return isSend
	 */
	public Integer getIsSend() {
		return isSend;
	}

	/**
	 * 是否发送成功（1:成功，0:失败）
	 * @param isSend
	 */
	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}

	/**
	 * 发送次数
	 * @return sendTimes
	 */
	public Integer getSendTimes() {
		return sendTimes;
	}

	/**
	 * 发送次数
	 * @param sendTimes
	 */
	public void setSendTimes(Integer sendTimes) {
		this.sendTimes = sendTimes;
	}

	/**
	 * 发送成功时间
	 * @return sendTime
	 */
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * 发送成功时间
	 * @param sendTime
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 联系人电话
	 * @return contactPhoneNo
	 */
	public String getContactPhoneNo() {
		return contactPhoneNo;
	}

	/**
	 * 联系人电话
	 * @param contactPhoneNo
	 */
	public void setContactPhoneNo(String contactPhoneNo) {
		this.contactPhoneNo = contactPhoneNo;
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
	 * 客退单编号
	 * @return returnNo
	 */
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
	 * 状态显示文字
	 * @return statusMsg
	 */
	public String getStatusMsg() {
		return statusMsg;
	}

	/**
	 * 状态显示文字
	 * @param statusMsg
	 */
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

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

	
	
	public Double getReturnMoney() {
		return returnMoney;
	}

	public void setReturnMoney(Double returnMoney) {
		this.returnMoney = returnMoney;
	}

	/**
	 * <p>设置退款单状态、并且修改状态文字</p>
	 * 
	 * <p>修改退款单状态、统一使用该方法</p>
	 * 
	 * <p>当设置status=200时，需要传快递公司名称和快递编号</p>
	 * 
	 * <p>当设置status=300时，需要传快退款金额</p>
	 * 
	 * @param bmsStatus
	 * @param expressCompany
	 * @param transportNo
	 * @param totalPrice
	 */
	public void setStatusAndMsg(String bmsStatus,String expressCompany,
			String transportNo,double totalPrice ){
		
			//"100";审核通过
		if(bmsStatus.equals(STATUS_INIT)){
			this.setStatus(STATUS_INIT);
			this.setStatusBms(STATUS_INIT);
			this.setStatusMsg(STATUS_INIT_MSG);
			
			//"200"等待寄回商品	
		}else if(bmsStatus.equals(STATUS_RECEIVEING)){
			
			if(expressCompany == null)
				expressCompany = StringUtils.EMPTY;
			if(transportNo == null)
				transportNo = StringUtils.EMPTY;
			
			this.setStatus(STATUS_RECEIVEING);
			this.setStatusBms(STATUS_RECEIVEING);
			this.setStatusMsg(STATUS_RECEIVEING_MSG.replaceAll("XXXX", expressCompany).replaceAll("YYYY", transportNo));
			
			//"299";已取消
		}else if(bmsStatus.equals(STATUS_CANCEL)){
			this.setStatus(STATUS_CANCEL);
			this.setStatusBms(STATUS_CANCEL);
			this.setStatusMsg(STATUS_CANCEL_MSG);
			
			//"300";已经发起退款
		}else if(bmsStatus.equals(STATUS_REFUNDING)){
			String msg = STATUS_REFUNDING_MSG.replaceAll("XXXX", totalPrice+StringUtils.EMPTY);
			this.setStatus(STATUS_REFUNDING);
			this.setStatusBms(STATUS_REFUNDING);
			this.setStatusMsg(msg);
			
			//"359";异常-建议退款
		}else if(bmsStatus.equals(STATUS_EXCEP_REFUND)){
			this.setStatus(STATUS_RECEIVEING);
			this.setStatusBms(STATUS_EXCEP_REFUND);
			this.setStatusMsg(STATUS_EXCEP_REFUND_MSG);
			
			//"369";异常-建议不退款
		}else if(bmsStatus.equals(STATUS_EXCEP_STOP)){
			this.setStatus(STATUS_RECEIVEING);
			this.setStatusBms(STATUS_EXCEP_STOP);
			this.setStatusMsg(STATUS_EXCEP_STOP_MSG);
			
			//"400";已退款
		}else if(bmsStatus.equals(STATUS_REFUND_FINISH)){
			this.setStatus(STATUS_REFUND_FINISH);
			this.setStatusBms(STATUS_REFUND_FINISH);
			this.setStatusMsg(STATUS_REFUND_FINISH_MSG);
		}
		
	}

	
}
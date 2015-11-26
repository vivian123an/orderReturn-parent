package me.quxiu.orderReturn.inf.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 发送给EBS客退申请单VO
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年10月21日 下午4:35:04
 * 
 */

public class InfEbsOrderReturnHeaderVO implements Serializable{
	
	private static final long serialVersionUID = -3361621590584883346L;

	/**
	 * 明细
	 */
	private List<InfEbsOrderReturnDetailVO> lines;
	
	/**
	 * 主键
	 */
    private Integer id;

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
     * 退货原因（1:产品与描述不符，2:不喜欢/不好看，3:做工瑕疵，4:发错货，5:商品包裹破损，
     * 6:漏发商品/附件，7:褪色掉色，8:颜色/款式与描述不符，9:材质/面料与描述不符，10:其他）
     */
    private Integer returnReason;

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
     * 客退申请单状态（100:新建，200:已发起退款，300:已退款，399:收货异常，400:已完成）
     */
    private String status;

    /**
     * 异常处理时间
     */
    private Date errorTime;
    
    /**
     * 联系人电话
     */
    private String contactPhoneNo;



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
	 * 退货原因（1:产品与描述不符，2:不喜欢不好看，3:做工瑕疵，4:发错货，5:商品包裹破损，6:漏发商品附件，7:褪色掉色，8:颜色款式与描述不符，9:材质面料与描述不符，10:其他）
	 * @return returnReason
	 */
	public Integer getReturnReason() {
		return returnReason;
	}

	/**
	 * 退货原因（1:产品与描述不符，2:不喜欢不好看，3:做工瑕疵，4:发错货，5:商品包裹破损，6:漏发商品附件，7:褪色掉色，8:颜色款式与描述不符，9:材质面料与描述不符，10:其他）
	 * @param returnReason
	 */
	public void setReturnReason(Integer returnReason) {
		this.returnReason = returnReason;
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
	 * 明细
	 * @return lines
	 */
	public List<InfEbsOrderReturnDetailVO> getLines() {
		return lines;
	}

	/**
	 * 明细
	 * @param lines
	 */
	public void setLines(List<InfEbsOrderReturnDetailVO> lines) {
		this.lines = lines;
	}


    
    
}

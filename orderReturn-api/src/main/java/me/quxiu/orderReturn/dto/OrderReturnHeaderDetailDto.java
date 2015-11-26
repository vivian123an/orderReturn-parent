package me.quxiu.orderReturn.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 客退单详情
 * @author wangmin
 *
 */
public class OrderReturnHeaderDetailDto implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1173314061988889039L;
	private String returnNo;//客退单号
	private String orderSn;//订单号
	private Integer orderId;
	private String transportNo;
	private String contactUser;
	private String contactPhoneNo;
	private String status;//客退单进度
	private String applyTime;//申请时间
	private String returnReason;//退货原因
	private double refundPrice;//退款金额
	private String imgUrl1;
	private String imgUrl2;
	private String imgUrl3;
	
	private String expressCode;//快递公司编号
	private String expressName;//快递公司名称
	
	
	
	
	
	private List<OrderItemDto> details;//商品明细
	private List<ReturnProcessLogDto> processLogs;//处理日志
	//客户备注信息
	private List<OrderReturnProcessRemarkDto> processRemarkDtos;
	
	
	
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getExpressCode() {
		return expressCode;
	}
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	public String getExpressName() {
		return expressName;
	}
	public void setExpressName(String expressName) {
		this.expressName = expressName;
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
	public String getTransportNo() {
		return transportNo;
	}
	public void setTransportNo(String transportNo) {
		this.transportNo = transportNo;
	}
	public String getContactUser() {
		return contactUser;
	}
	public void setContactUser(String contactUser) {
		this.contactUser = contactUser;
	}
	public String getContactPhoneNo() {
		return contactPhoneNo;
	}
	public void setContactPhoneNo(String contactPhoneNo) {
		this.contactPhoneNo = contactPhoneNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public List<OrderItemDto> getDetails() {
		return details;
	}
	public void setDetails(List<OrderItemDto> details) {
		this.details = details;
	}
	public List<ReturnProcessLogDto> getProcessLogs() {
		return processLogs;
	}
	public void setProcessLogs(List<ReturnProcessLogDto> processLogs) {
		this.processLogs = processLogs;
	}
	public List<OrderReturnProcessRemarkDto> getProcessRemarkDtos() {
		return processRemarkDtos;
	}
	public void setProcessRemarkDtos(
			List<OrderReturnProcessRemarkDto> processRemarkDtos) {
		this.processRemarkDtos = processRemarkDtos;
	}
	public double getRefundPrice() {
		return refundPrice;
	}
	public void setRefundPrice(double refundPrice) {
		this.refundPrice = refundPrice;
	}
	public String getImgUrl1() {
		return imgUrl1;
	}
	public void setImgUrl1(String imgUrl1) {
		this.imgUrl1 = imgUrl1;
	}
	public String getImgUrl2() {
		return imgUrl2;
	}
	public void setImgUrl2(String imgUrl2) {
		this.imgUrl2 = imgUrl2;
	}
	public String getImgUrl3() {
		return imgUrl3;
	}
	public void setImgUrl3(String imgUrl3) {
		this.imgUrl3 = imgUrl3;
	}
	
	
	
	
	
	
	
	

}

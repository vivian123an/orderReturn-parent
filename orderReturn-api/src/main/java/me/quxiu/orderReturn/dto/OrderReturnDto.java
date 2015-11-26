package me.quxiu.orderReturn.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 我的售后单列表DTO
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年11月6日 下午2:08:50
 * 
 */

public class OrderReturnDto implements Serializable{

	private static final long serialVersionUID = 2741600763408157126L;
	
	private String orderSn;//订单号
	private String returnNo;//客退单号
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private String applyTime;//申请时间
	private Double refund;//退款金额
	private String status;//客退单进度
	
	private List<OrderItemDto> details;//商品明细
	private List<ReturnProcessLogDto> processLogs;//处理日志
	
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	public String getReturnNo() {
		return returnNo;
	}
	public void setReturnNo(String returnNo) {
		this.returnNo = returnNo;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public Double getRefund() {
		return refund;
	}
	public void setRefund(Double refund) {
		this.refund = refund;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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

	
	
	
}

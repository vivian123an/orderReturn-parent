package me.quxiu.orderReturn.inf.dto;

import java.io.Serializable;

public class EbsRefundStatusItemDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1295115281104296507L;
	
	private Long id;
	
	private Long refund_id;
	
	private Long order_id;
	
	private String order_status;
	
	private String memo;
	
	private String refund_date;
	
	private Long order_return_header_id;
	
	
	

	public Long getOrder_return_header_id() {
		return order_return_header_id;
	}

	public void setOrder_return_header_id(Long order_return_header_id) {
		this.order_return_header_id = order_return_header_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(Long refund_id) {
		this.refund_id = refund_id;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getRefund_date() {
		return refund_date;
	}

	public void setRefund_date(String refund_date) {
		this.refund_date = refund_date;
	}
	
	
	
	
	
	

}
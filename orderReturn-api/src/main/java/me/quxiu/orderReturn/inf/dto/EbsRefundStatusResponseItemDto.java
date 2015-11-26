package me.quxiu.orderReturn.inf.dto;

import java.io.Serializable;

public class EbsRefundStatusResponseItemDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2795232544324103820L;
	
	
	private Long id;
	
	private boolean error;
	
	private String message;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}

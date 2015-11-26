package me.quxiu.orderReturn.inf.dto;

import java.io.Serializable;

public class EbsCuxArRecFrb2cItemResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4793254135490099986L;
	private Long TRX_ID;
	private String ORDER_ID;
	private String ORDER_NUM;
	private String EBS_PROCESS_FLAG;
	private String ERR_MSG;
	
	
	public Long getTRX_ID() {
		return TRX_ID;
	}
	public void setTRX_ID(Long tRX_ID) {
		TRX_ID = tRX_ID;
	}
	public String getORDER_ID() {
		return ORDER_ID;
	}
	public void setORDER_ID(String oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}
	public String getORDER_NUM() {
		return ORDER_NUM;
	}
	public void setORDER_NUM(String oRDER_NUM) {
		ORDER_NUM = oRDER_NUM;
	}
	public String getEBS_PROCESS_FLAG() {
		return EBS_PROCESS_FLAG;
	}
	public void setEBS_PROCESS_FLAG(String eBS_PROCESS_FLAG) {
		EBS_PROCESS_FLAG = eBS_PROCESS_FLAG;
	}
	public String getERR_MSG() {
		return ERR_MSG;
	}
	public void setERR_MSG(String eRR_MSG) {
		ERR_MSG = eRR_MSG;
	}
	
	
	
	
	
	

}

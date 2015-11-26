package me.quxiu.orderReturn.inf.dto;

import java.io.Serializable;
import java.util.List;

/**
 * * 客退申请EBS返回
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年10月23日 下午3:58:40
 * 
 */

public class EbsArTrxFrb2cHResponse implements Serializable{
	
	private static final long serialVersionUID = -78484085728249335L;
	
	private String TRX_ID;
	private List<EbsArTrxFrb2cLResponse> LINES;
	private String HEADER_PROCESS_FLAG;
	private String HEADER_ERR_MSG;
	
	public String getTRX_ID() {
		return TRX_ID;
	}
	public void setTRX_ID(String tRX_ID) {
		TRX_ID = tRX_ID;
	}
	public List<EbsArTrxFrb2cLResponse> getLINES() {
		return LINES;
	}
	public void setLINES(List<EbsArTrxFrb2cLResponse> lINES) {
		LINES = lINES;
	}
	public String getHEADER_PROCESS_FLAG() {
		return HEADER_PROCESS_FLAG;
	}
	public void setHEADER_PROCESS_FLAG(String hEADER_PROCESS_FLAG) {
		HEADER_PROCESS_FLAG = hEADER_PROCESS_FLAG;
	}
	public String getHEADER_ERR_MSG() {
		return HEADER_ERR_MSG;
	}
	public void setHEADER_ERR_MSG(String hEADER_ERR_MSG) {
		HEADER_ERR_MSG = hEADER_ERR_MSG;
	}
	
	
	
}

package me.quxiu.orderReturn.inf.dto;

import java.io.Serializable;

/**
 * 
 * 客退申请EBS返回，明细
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年10月23日 下午3:59:18
 * 
 */

public class EbsArTrxFrb2cLResponse implements Serializable{

	private static final long serialVersionUID = 2748367516386298090L;
	
	private Integer LINE_NUM;
	private String ITEM_NO;
	private String LINE_PROCESS_FLAG;
	private String LINE_ERR_MSG;
	
	
	
	public Integer getLINE_NUM() {
		return LINE_NUM;
	}
	public void setLINE_NUM(Integer lINE_NUM) {
		LINE_NUM = lINE_NUM;
	}
	public String getITEM_NO() {
		return ITEM_NO;
	}
	public void setITEM_NO(String iTEM_NO) {
		ITEM_NO = iTEM_NO;
	}
	public String getLINE_PROCESS_FLAG() {
		return LINE_PROCESS_FLAG;
	}
	public void setLINE_PROCESS_FLAG(String lINE_PROCESS_FLAG) {
		LINE_PROCESS_FLAG = lINE_PROCESS_FLAG;
	}
	public String getLINE_ERR_MSG() {
		return LINE_ERR_MSG;
	}
	public void setLINE_ERR_MSG(String lINE_ERR_MSG) {
		LINE_ERR_MSG = lINE_ERR_MSG;
	}
	
	
	
	
	
	
	
}

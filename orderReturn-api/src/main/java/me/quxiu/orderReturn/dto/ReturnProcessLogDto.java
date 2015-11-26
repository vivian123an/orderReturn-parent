package me.quxiu.orderReturn.dto;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 客退单处理日志DTO
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年11月6日 下午2:22:36
 * 
 */

public class ReturnProcessLogDto implements Serializable{

	private static final long serialVersionUID = -2195388263501985614L;
	/**
	 * 处理信息
	 */
	private String processMsg;
	/**
	 * 处理时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private String processTime;
	/**
	 * 客退单号
	 */
	private String returnNo;
	
	/**
	 * 处理人
	 */
	private String processUser;
	
	
	
	public String getProcessUser() {
		return processUser;
	}
	public void setProcessUser(String processUser) {
		this.processUser = processUser;
	}
	/**
	 * 处理信息
	 * @return processMsg
	 */
	public String getProcessMsg() {
		return processMsg;
	}
	/**
	 * 处理信息
	 * @param processMsg
	 */
	public void setProcessMsg(String processMsg) {
		this.processMsg = processMsg;
	}
	/**
	 * 处理时间
	 * @return processTime
	 */
	public String getProcessTime() {
		return processTime;
	}
	/**
	 * 处理时间
	 * @param processTime
	 */
	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}
	/**
	 * 客退单号
	 * @return returnNo
	 */
	public String getReturnNo() {
		return returnNo;
	}
	/**
	 * 客退单号
	 * @param returnNo
	 */
	public void setReturnNo(String returnNo) {
		this.returnNo = returnNo;
	}

	
	
}

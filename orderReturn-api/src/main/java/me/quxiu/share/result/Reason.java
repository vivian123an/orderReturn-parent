package me.quxiu.share.result;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年9月7日 下午5:45:30
 * 
 */

public class Reason implements Serializable {

	private static final long serialVersionUID = -8794409324121418468L;
	
	/**
	 * 业务处理逻辑成功
	 */
	public static final String REASON_CODE_SUCCESS = "0000";
	/**
	 * 请求参数错误编码
	 */
	public static final String REASON_CODE_REQUEST_ERROR = "400";
	/**
	 * 请求参数错误编码（主键空）
	 */
	public static final String REASON_CODE_ERROR_NULLID = "401";
	/**
	 * 服务内部错误编码
	 */
	public static final String REASON_CODE_SERVER_ERROR = "500";
	
	/**
	 * type字段默认值
	 */
	public static final Integer REASON_TYPE_DEFAULT = 0;
	
	/**
	 * 返回信息
	 */
	private String code = REASON_CODE_SUCCESS;
	/**
	 * 预留
	 */
	private Integer type = REASON_TYPE_DEFAULT;
	/**
	 * 返回信息
	 */
	private String msg = StringUtils.EMPTY;
	/**
	 * 返回信息代码
	 * @return code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 返回信息代码
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 预留
	 * @return type
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 预留
	 * @param type
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 返回信息
	 * @return msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * 返回信息
	 * @param msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	
}

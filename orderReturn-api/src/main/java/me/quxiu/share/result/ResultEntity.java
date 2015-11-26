package me.quxiu.share.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.quxiu.share.query.BaseQuery;

/**
 * 针对趣秀前端框架定义的返回对象
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年9月7日 下午5:42:05
 * 
 */

public class ResultEntity<T> implements Serializable{
	
	private static final long serialVersionUID = 4981497296158297367L;

	/**
	 * 请求成功编码
	 */
	public static final Integer STATUSCODE_OK = 200;
	
	/**
	 * 200代表返回数据成功
	 */
	private  Integer statusCode = STATUSCODE_OK;
	
	private ResponseBody<T> responseBody = new ResponseBody<T>();
	
	/**
	 * 200代表返回数据成功
	 * @return statusCode
	 */
	public Integer getStatusCode() {
		return statusCode;
	}

	/**
	 * 200代表返回数据成功
	 * @param statusCode
	 */
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public ResponseBody<T> getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(ResponseBody<T> responseBody) {
		this.responseBody = responseBody;
	}

	/**
	 * 设置reason实体msg值
	 * @param msg
	 */
	public void setReasonMsg(String msg){
		this.getResponseBody().getResponseInfo().getReasons().setMsg(msg);
	}
	
	/**
	 * 设置reason实体code值
	 * @param code
	 */
	public void setReasonCode(String code){
		this.getResponseBody().getResponseInfo().getReasons().setCode(code);
	}
	
	/**
	 * 设置查询对象
	 */
	public void setQuery(BaseQuery query){
		this.getResponseBody().setQuery(query);
	}

	
	
}

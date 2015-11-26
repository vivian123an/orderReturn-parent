package me.quxiu.share.result;

import java.io.Serializable;
import java.util.List;

import me.quxiu.share.query.BaseQuery;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年9月7日 下午5:50:11
 * 
 */

public class ResponseBody<T> implements Serializable {

	private static final long serialVersionUID = 9013859616185884178L;
	
	private ResponseInfo responseInfo = new ResponseInfo();
	
	private List<T> datas;
	
	private T data;
	
	private BaseQuery query;

	public ResponseInfo getResponseInfo() {
		return responseInfo;
	}

	public void setResponseInfo(ResponseInfo responseInfo) {
		this.responseInfo = responseInfo;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public BaseQuery getQuery() {
		return query;
	}

	public void setQuery(BaseQuery query) {
		this.query = query;
	}

	
}

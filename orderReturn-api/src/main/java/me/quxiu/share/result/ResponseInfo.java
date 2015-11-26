package me.quxiu.share.result;

import java.io.Serializable;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年9月7日 下午5:46:29
 * 
 */

public class ResponseInfo implements Serializable {

	private static final long serialVersionUID = -4430866987249838062L;
	
	private Reason reasons = new Reason();

	/**
	 * reasons
	 * @return reasons
	 */
	public Reason getReasons() {
		return reasons;
	}

	/**
	 * reasons
	 * @param reasons
	 */
	public void setReasons(Reason reasons) {
		this.reasons = reasons;
	}
	
	
	
}

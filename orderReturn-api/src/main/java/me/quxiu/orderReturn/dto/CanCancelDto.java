package me.quxiu.orderReturn.dto;

import java.io.Serializable;

/**
 * 判断是否可以取消退货
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年11月13日 下午12:11:01
 * 
 */

public class CanCancelDto implements Serializable{

	private static final long serialVersionUID = -3012781119849436036L;
	/**
	 * 是否可以取消退货
	 */
	private boolean canCancel;
	
	
	/**
	 * 是否可以取消退货
	 * @return canCancel
	 */
	public boolean isCanCancel() {
		return canCancel;
	}
	/**
	 * 是否可以取消退货
	 * @param canCancel
	 */
	public void setCanCancel(boolean canCancel) {
		this.canCancel = canCancel;
	}
	
	
	
}

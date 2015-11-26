package me.quxiu.orderReturn.query;

import java.io.Serializable;

import me.quxiu.share.query.BaseQuery;

import org.apache.commons.lang.StringUtils;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年10月22日 下午6:36:41
 * 
 */

public class OrderReturnHeaderQuery extends BaseQuery implements Serializable{
	
	private static final long serialVersionUID = -7953468145535170295L;

	/**
	 * 主键
	 */
    private Integer id;

    /**
     * 原始订单ID
     */
    private Integer orderId;

    /**
     * 原始订单号
     */
    private String orderSn;

    /**
     * 会员ID
     */
    private Integer userId;
    
    
    
    private String returnNo;
    
    private String contactPhoneNo;
    
    private String transportNo;
    
    private String statusBms;
    
    private String createTimeStart;
    
    private String createTimeEnd;
    
    
    public OrderReturnHeaderQuery(){
    	
    	orderSn = StringUtils.EMPTY;
    	returnNo = StringUtils.EMPTY;
    	contactPhoneNo = StringUtils.EMPTY;
    	transportNo = StringUtils.EMPTY;
    	statusBms = StringUtils.EMPTY;
    	createTimeStart = StringUtils.EMPTY;
    	createTimeEnd = StringUtils.EMPTY;
    }
    


	public String getReturnNo() {
		return returnNo;
	}

	public void setReturnNo(String returnNo) {
		this.returnNo = returnNo;
	}

	public String getContactPhoneNo() {
		return contactPhoneNo;
	}

	public void setContactPhoneNo(String contactPhoneNo) {
		this.contactPhoneNo = contactPhoneNo;
	}

	public String getTransportNo() {
		return transportNo;
	}

	public void setTransportNo(String transportNo) {
		this.transportNo = transportNo;
	}

	public String getStatusBms() {
		return statusBms;
	}

	public void setStatusBms(String statusBms) {
		this.statusBms = statusBms;
	}


	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	/**
	 * 主键
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 原始订单ID
	 * @return orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * 原始订单ID
	 * @param orderId
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * 原始订单号
	 * @return orderSn
	 */
	public String getOrderSn() {
		return orderSn;
	}

	/**
	 * 原始订单号
	 * @param orderSn
	 */
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	/**
	 * 会员ID
	 * @return userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 会员ID
	 * @param userId
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
    
    

}

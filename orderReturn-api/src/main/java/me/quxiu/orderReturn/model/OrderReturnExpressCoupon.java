package me.quxiu.orderReturn.model;

import java.io.Serializable;

import me.quxiu.share.model.BaseEntity;

/**
 * 客退邮费补差表
 * @author  wenan.chen@quxiu.me
 * @version 2015年11月17日 下午2:20:53
 */

public class OrderReturnExpressCoupon extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 4363961887241505544L;

	private Integer id;

    private Integer orderId;

    private String orderSn;

    private String returnNo;

    private Integer supplierId;
    
    private Boolean isMakeup;

    private Float makeupMoney;

    private Boolean isSendCoupon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public String getReturnNo() {
        return returnNo;
    }

    public void setReturnNo(String returnNo) {
        this.returnNo = returnNo == null ? null : returnNo.trim();
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Boolean getIsMakeup() {
        return isMakeup;
    }

    public void setIsMakeup(Boolean isMakeup) {
        this.isMakeup = isMakeup;
    }

    public Float getMakeupMoney() {
        return makeupMoney;
    }

    public void setMakeupMoney(Float makeupMoney) {
        this.makeupMoney = makeupMoney;
    }

    public Boolean getIsSendCoupon() {
        return isSendCoupon;
    }

    public void setIsSendCoupon(Boolean isSendCoupon) {
        this.isSendCoupon = isSendCoupon;
    }
}
package me.quxiu.orderReturn.model;

import java.util.Date;

public class InfExceptionLog {
    private Integer id;

    private String exMsg;

    private String exId;

    private String exDescription;

    private String infCode;

    private Date createTime;

    private String createBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExMsg() {
        return exMsg;
    }

    public void setExMsg(String exMsg) {
        this.exMsg = exMsg == null ? null : exMsg.trim();
    }

    public String getExId() {
        return exId;
    }

    public void setExId(String exId) {
        this.exId = exId;
    }

	public String getExDescription() {
		return exDescription;
	}

	public void setExDescription(String exDescription) {
		this.exDescription = exDescription;
	}

	public String getInfCode() {
        return infCode;
    }

    public void setInfCode(String infCode) {
        this.infCode = infCode == null ? null : infCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }
}
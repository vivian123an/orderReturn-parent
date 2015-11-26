package me.quxiu.share.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 实体基础类
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年9月15日 上午10:59:24
 * 
 */

public class BaseEntity {

	/**
     * 0:未删除，1：已删除
     */
    private Boolean isDelete;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新人
     */
    private String updateBy;
	/**
	 * 0:未删除，1：已删除
	 * @return isDelete
	 */
	public Boolean getIsDelete() {
		return isDelete;
	}
	/**
	 * 0:未删除，1：已删除
	 * @param isDelete
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 创建时间
	 * @return createTime
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 创建时间
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 创建人
	 * @return createBy
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 创建人
	 * @param createBy
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 更新时间
	 * @return updateTime
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 更新时间
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 更新人
	 * @return updateBy
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 更新人
	 * @param updateBy
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
    
    
}

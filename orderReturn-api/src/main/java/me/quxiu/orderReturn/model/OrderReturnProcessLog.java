package me.quxiu.orderReturn.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 退货申请处理日记
 * @author  wenan.chen@quxiu.me
 * @version 2015年11月5日 下午5:15:22
 */

public class OrderReturnProcessLog implements Serializable{
	
	private static final long serialVersionUID = 8106596057940139506L;

	private Integer id;

    private String returnNo;

    private String status;

    private String statusBms;

    private String statusMsg;

    private String processUser;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReturnNo() {
        return returnNo;
    }

    public void setReturnNo(String returnNo) {
        this.returnNo = returnNo == null ? null : returnNo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getStatusBms() {
        return statusBms;
    }

    public void setStatusBms(String statusBms) {
        this.statusBms = statusBms == null ? null : statusBms.trim();
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg == null ? null : statusMsg.trim();
    }

    public String getProcessUser() {
        return processUser;
    }

    public void setProcessUser(String processUser) {
        this.processUser = processUser == null ? null : processUser.trim();
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }
    
	/**
	 * <p>设置退款单状态、并且修改状态文字</p>
	 * 
	 * <p>修改退款单状态、统一使用该方法</p>
	 * 
	 * <p>当设置status=200时，需要传快递公司名称和快递编号</p>
	 * 
	 * <p>当设置status=300时，需要传快退款金额</p>
	 * 
	 * @param bmsStatus
	 * @param expressCompany
	 * @param transportNo
	 * @param totalPrice
	 */
	public void setStatusAndMsg(String bmsStatus,String expressCompany,
			String transportNo,double totalPrice ){
		
			//"100";审核通过
		if(bmsStatus.equals(OrderReturnHeader.STATUS_INIT)){
			this.setStatus(OrderReturnHeader.STATUS_INIT);
			this.setStatusBms(OrderReturnHeader.STATUS_INIT);
			this.setStatusMsg(OrderReturnHeader.STATUS_INIT_MSG);
			
			//"200"等待寄回商品	
		}else if(bmsStatus.equals(OrderReturnHeader.STATUS_RECEIVEING)){
			
			if(expressCompany == null)
				expressCompany = StringUtils.EMPTY;
			if(transportNo == null)
				transportNo = StringUtils.EMPTY;
			
			this.setStatus(OrderReturnHeader.STATUS_RECEIVEING);
			this.setStatusBms(OrderReturnHeader.STATUS_RECEIVEING);
			this.setStatusMsg(OrderReturnHeader.STATUS_RECEIVEING_MSG.replaceAll("XXXX", expressCompany).replaceAll("YYYY", transportNo));
			
			//"299";已取消
		}else if(bmsStatus.equals(OrderReturnHeader.STATUS_CANCEL)){
			this.setStatus(OrderReturnHeader.STATUS_CANCEL);
			this.setStatusBms(OrderReturnHeader.STATUS_CANCEL);
			this.setStatusMsg(OrderReturnHeader.STATUS_CANCEL_MSG);
			
			//"300";已经发起退款
		}else if(bmsStatus.equals(OrderReturnHeader.STATUS_REFUNDING)){
			String msg = OrderReturnHeader.STATUS_REFUNDING_MSG.replaceAll("XXXX", totalPrice+StringUtils.EMPTY);
			this.setStatus(OrderReturnHeader.STATUS_REFUNDING);
			this.setStatusBms(OrderReturnHeader.STATUS_REFUNDING);
			this.setStatusMsg(msg);
			
			//"359";异常-建议退款
		}else if(bmsStatus.equals(OrderReturnHeader.STATUS_EXCEP_REFUND)){
			this.setStatus(OrderReturnHeader.STATUS_RECEIVEING);
			this.setStatusBms(OrderReturnHeader.STATUS_EXCEP_REFUND);
			this.setStatusMsg(OrderReturnHeader.STATUS_EXCEP_REFUND_MSG);
			
			//"369";异常-建议不退款
		}else if(bmsStatus.equals(OrderReturnHeader.STATUS_EXCEP_STOP)){
			this.setStatus(OrderReturnHeader.STATUS_RECEIVEING);
			this.setStatusBms(OrderReturnHeader.STATUS_EXCEP_STOP);
			this.setStatusMsg(OrderReturnHeader.STATUS_EXCEP_STOP_MSG);
			
			//"400";已退款
		}else if(bmsStatus.equals(OrderReturnHeader.STATUS_REFUND_FINISH)){
			this.setStatus(OrderReturnHeader.STATUS_REFUND_FINISH);
			this.setStatusBms(OrderReturnHeader.STATUS_REFUND_FINISH);
			this.setStatusMsg(OrderReturnHeader.STATUS_REFUND_FINISH_MSG);
		}
		
	}
}
package me.quxiu.orderReturn.service;

public interface EbsFeedBackRefundStatusService {
	
	/**
	 * 更新退款单状态
	 * @param xmlStr
	 * @return
	 */
	public String updateReFundStatus(String xmlStr);

}

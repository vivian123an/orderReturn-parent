package me.quxiu.orderReturn.inf.ebs.service;

/**
 * F类接口JOB继实现接口
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年10月21日 下午4:14:48
 * 
 */
public interface JobInfBaseServiceF {

	/**
	 * 传输系统中接口表的数据
	 */
	public void saveTransmit();

	/**
	 * 传送失败的接口表数据，会重复发送几次
	 */
	public void saveTransmitByPriority();
}

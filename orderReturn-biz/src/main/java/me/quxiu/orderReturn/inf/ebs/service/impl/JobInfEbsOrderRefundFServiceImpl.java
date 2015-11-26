package me.quxiu.orderReturn.inf.ebs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.quxiu.orderReturn.inf.dto.EbsCuxArRecFrb2c;
import me.quxiu.orderReturn.inf.dto.EbsCuxArRecFrb2cItem;
import me.quxiu.orderReturn.inf.dto.EbsCuxArRecFrb2cItemResponse;
import me.quxiu.orderReturn.inf.dto.EbsCuxArRecFrb2cResponse;
import me.quxiu.orderReturn.inf.ebs.service.JobInfEbsOrderRefundFService;
import me.quxiu.orderReturn.inf.util.InfConstant;
import me.quxiu.orderReturn.inf.util.WsClientUtil;
import me.quxiu.orderReturn.inf.util.WsParam;
import me.quxiu.orderReturn.mapper.InfConfigurationMapper;
import me.quxiu.orderReturn.mapper.InfEbsOrderRefundMapper;
import me.quxiu.orderReturn.mapper.InfExceptionLogMapper;
import me.quxiu.orderReturn.model.InfEbsOrderRefund;
import me.quxiu.orderReturn.model.InfExceptionLog;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class JobInfEbsOrderRefundFServiceImpl implements
		JobInfEbsOrderRefundFService {

	private static final String WS_METHOD = "allWsdataToebs";
	private static final String WS_NAMESPACE = "http://qxdbtest/Qxalltoebs.wsdl";
	private static final String CONFIG_REQUEST_URL = "B2C_EBS_REFUND_001_REQUEST_URL";
	private static final String CONFIG_REQUSET_MAX_NUM = "B2C_EBS_REFUND_001_REQUSET_MAX_NUM";
	private static final String RESPONSE_SUCCESS_FLAG = "S";
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private InfConfigurationMapper infConfigurationMapper;
	@Autowired
	private InfExceptionLogMapper infExceptionLogMapper;
	@Autowired
	private InfEbsOrderRefundMapper infEbsOrderRefundMapper;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = { Exception.class })
	public void saveTransmit() {
		// TODO Auto-generated method stub
		// 接口每次发送数据量
		String maxCount = infConfigurationMapper
				.getByKey(CONFIG_REQUSET_MAX_NUM);
		List<InfEbsOrderRefund> orderRefunds = infEbsOrderRefundMapper
				.getUnSendOrderRefundsBySendTimes(0, Integer.valueOf(maxCount));
		doTransmit(orderRefunds);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = { Exception.class })
	public void saveTransmitByPriority() {

		// 接口每次发送数据量
		String maxCount = infConfigurationMapper
				.getByKey(CONFIG_REQUSET_MAX_NUM);
		List<InfEbsOrderRefund> orderRefunds = infEbsOrderRefundMapper
				.getUnSendOrderRefundsBySendTimes(0, Integer.valueOf(maxCount));
		doTransmit(orderRefunds);

	}

	private void doTransmit(List<InfEbsOrderRefund> orderRefunds) {

		if (orderRefunds != null && orderRefunds.size() > 0) {
			EbsCuxArRecFrb2c ebsCuxArRecFrb2c = toEbsCuxArRecFrb2c(orderRefunds);
			XStream xs = new XStream(new StaxDriver(new NoNameCoder()));
			xs.alias("HEADERS", EbsCuxArRecFrb2c.class);
			xs.alias("HEADER", EbsCuxArRecFrb2cItem.class);
			xs.addImplicitCollection(EbsCuxArRecFrb2c.class, "items");
			String xml = xs.toXML(ebsCuxArRecFrb2c);

			List<WsParam> wsParams = new ArrayList<WsParam>();
			wsParams.add(new WsParam("pXmlIn", xml));
			wsParams.add(new WsParam("pType", "CASH"));
			
			
			// 调用服务端 推送数据
			String request_url = infConfigurationMapper.getByKey(CONFIG_REQUEST_URL);
			String responseXml = WsClientUtil.axisInvokeWs(request_url, WS_NAMESPACE, WS_METHOD, wsParams);
			
			if (StringUtils.isNotBlank(responseXml)) {
				
				xs.alias("HEADERS", EbsCuxArRecFrb2cResponse.class);
				xs.alias("HEADER", EbsCuxArRecFrb2cItemResponse.class);
				xs.addImplicitCollection(EbsCuxArRecFrb2cResponse.class, "items");
				EbsCuxArRecFrb2cResponse response =null;
				try {
					 response = (EbsCuxArRecFrb2cResponse)xs.fromXML(responseXml);
				} catch (Exception e) {
					InfExceptionLog log = new InfExceptionLog();
					log.setCreateTime(new Date());
					log.setExMsg(e.getMessage());
					log.setExDescription("返回报文格式错误");
					log.setInfCode(InfConstant.InfCodes.INF_EBS_ORDER_RETURN_REFUND_F_CODE);
					infExceptionLogMapper.save(log);
				}
				List<Long> successIdList= parseSuccessIds(response.getItems());
				try {
					if(successIdList!=null&&successIdList.size()>0){
						infEbsOrderRefundMapper.updateSendStatus(successIdList);
					}
				} catch (Throwable e) {
					logger.error(e.getMessage(),e);
					e.printStackTrace();
					InfExceptionLog log = new InfExceptionLog();
					log.setCreateTime(new Date());
					log.setExId(successIdList.toString());
					log.setExMsg(e.getMessage());
					log.setExDescription("修改发送成功标志出错");
					log.setInfCode(InfConstant.InfCodes.INF_EBS_ORDER_RETURN_REFUND_F_CODE);
					infExceptionLogMapper.save(log);
				}
				
			}
		}

	}
	
	
	private List<Long> parseSuccessIds(List<EbsCuxArRecFrb2cItemResponse> items) {
		List<Long> refundIds = new ArrayList<Long>();
		for(EbsCuxArRecFrb2cItemResponse item:items){
			if(RESPONSE_SUCCESS_FLAG.equals(item.getEBS_PROCESS_FLAG())){
				refundIds.add(item.getTRX_ID());
			}else{
				try {
					// 修改报文发送次数, 发送一次 累加一次, 发送次数作为发送优先级
					Map<String,Object> params = new HashMap<String,Object>();
					params.put("refundId", item.getTRX_ID());
					params.put("errMsg", item.getERR_MSG());
					infEbsOrderRefundMapper.updateSendTimesAdd(params);
				} catch (Throwable e) {
					logger.error(e.getMessage(),e);
					e.printStackTrace();
					InfExceptionLog log = new InfExceptionLog();
					log.setCreateTime(new Date());
					log.setExId(String.valueOf(item.getTRX_ID()));
					log.setExMsg(e.getMessage());
					log.setExDescription("修改发送次数出错");
					log.setInfCode(InfConstant.InfCodes.INF_EBS_ORDER_RETURN_REFUND_F_CODE);
					infExceptionLogMapper.save(log);
				}
			}
		}
		return refundIds;
	}
	
	

	private EbsCuxArRecFrb2c toEbsCuxArRecFrb2c(
			List<InfEbsOrderRefund> orderRefunds) {

		EbsCuxArRecFrb2c ebsCuxArRecFrb2c = new EbsCuxArRecFrb2c();
		List<EbsCuxArRecFrb2cItem> items = new ArrayList<EbsCuxArRecFrb2cItem>();
		for (InfEbsOrderRefund orderRefund : orderRefunds) {

			EbsCuxArRecFrb2cItem item = new EbsCuxArRecFrb2cItem();
			item.setOU_NAME("");
			item.setORDER_NUM(orderRefund.getOrderSn());
			item.setORDER_ID(orderRefund.getOrderId());
			item.setORDER_ATTACH_NUM(orderRefund.getOrderSn());
			item.setREC_DATE(orderRefund.getCreateTime());
			item.setGL_DATE(new Date());
			item.setBUSINESS_TYPE("退货退款应收");
			item.setSUPPLIER_ID(orderRefund.getSupplierId());
			item.setPAY_METHOD(orderRefund.getPayMethod());
			item.setPAY_TYPE(orderRefund.getPayType());
			item.setSHIPPER(StringUtils.EMPTY);// 物流公司名称 承运人（我方发货的）
			item.setSTATUS(StringUtils.EMPTY);
			item.setAMOUNT(orderRefund.getReturnAmount());// 需要退款的金额
			item.setDISCOUNT_AMOUNT(null);
			item.setNET_AMOUNT(null);
			item.setLINE_DESC("退货退款行");
			item.setRECEIPT_METHOD(StringUtils.EMPTY);
			items.add(item);

		}
		ebsCuxArRecFrb2c.setItems(items);
		return ebsCuxArRecFrb2c;
	}
}

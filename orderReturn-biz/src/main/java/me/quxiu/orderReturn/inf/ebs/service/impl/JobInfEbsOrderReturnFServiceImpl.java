package me.quxiu.orderReturn.inf.ebs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import me.quxiu.orderReturn.inf.dto.EbsArTrxFrb2cHResponse;
import me.quxiu.orderReturn.inf.dto.EbsCuxArTrxFrb2cH;
import me.quxiu.orderReturn.inf.dto.EbsCuxArTrxFrb2cL;
import me.quxiu.orderReturn.inf.ebs.service.JobInfEbsOrderReturnFService;
import me.quxiu.orderReturn.inf.util.InfConstant;
import me.quxiu.orderReturn.inf.util.WsClientUtil;
import me.quxiu.orderReturn.inf.util.WsParam;
import me.quxiu.orderReturn.inf.util.XmlTranslateUtil;
import me.quxiu.orderReturn.mapper.InfConfigurationMapper;
import me.quxiu.orderReturn.mapper.InfExceptionLogMapper;
import me.quxiu.orderReturn.mapper.OrderReturnDetailMapper;
import me.quxiu.orderReturn.mapper.OrderReturnHeaderMapper;
import me.quxiu.orderReturn.model.InfExceptionLog;
import me.quxiu.orderReturn.model.OrderReturnDetail;
import me.quxiu.orderReturn.model.OrderReturnHeader;
import me.quxiu.orderReturn.util.DateFormatUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 接口编码(infCode): B2C_EBS_001
 * 
 * <p>客退申请单发送EBS接口
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年10月21日 下午4:12:37
 * 
 */

@Service("jobInfEbsOrderReturnFService")
public class JobInfEbsOrderReturnFServiceImpl implements JobInfEbsOrderReturnFService{

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String WS_METHOD = "allWsdataToebs";
	private static final String WS_NAMESPACE = "http://qxdbtest/Qxalltoebs.wsdl";
	private static final String CONFIG_REQUEST_URL = "B2C_EBS_001_REQUEST_URL";
	private static final String CONFIG_REQUSET_MAX_NUM = "B2C_EBS_001_REQUSET_MAX_NUM";
	private static final String RESPONSE_SUCCESS_FLAG = "S";
	
	
	
	@Autowired
	private OrderReturnHeaderMapper orderReturnHeaderMapper;
	@Autowired
	private OrderReturnDetailMapper orderReturnDetailMapper;
	@Autowired
	private InfConfigurationMapper infConfigurationMapper;
	@Autowired
	private InfExceptionLogMapper infExceptionLogMapper;
	
	
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED , isolation= Isolation.READ_COMMITTED ,rollbackFor = { Exception.class })
	public void saveTransmit(){
		//接口每次发送数据量
		String maxCount = infConfigurationMapper.getByKey(CONFIG_REQUSET_MAX_NUM);
		List<OrderReturnHeader> headers = orderReturnHeaderMapper.getUnSendHeadersBySendTimes(0,Integer.valueOf(maxCount));
		
		doTransmit(headers);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED , isolation= Isolation.READ_COMMITTED ,rollbackFor = { Exception.class })
	public void saveTransmitByPriority(){
		//接口每次发送数据量
		String maxCount = infConfigurationMapper.getByKey(CONFIG_REQUSET_MAX_NUM);
		List<OrderReturnHeader> headers = orderReturnHeaderMapper.getUnSendHeadersBySendTimes(3,Integer.valueOf(maxCount));
		
		doTransmit(headers);
	}

	private void doTransmit(List<OrderReturnHeader> headers) {
		if(CollectionUtils.isNotEmpty(headers)){
			
			List<Integer> headerIds = new ArrayList<Integer>();
			for(OrderReturnHeader header : headers){
				headerIds.add(header.getId());
			}
			List<EbsCuxArTrxFrb2cH> headerVOs = copyToVo(headers);
			
			// 把VO数组转化成xml
			Class<?> requestAliasHeader = EbsCuxArTrxFrb2cH.class;
			Class<?> requestAliasLine = EbsCuxArTrxFrb2cL.class;
			String xml = XmlTranslateUtil.POJOToXML(headerVOs, requestAliasHeader,requestAliasLine);
			
			//logger.debug(InfConstant.logInfo.LOG_REQUEST + xml);
			List<WsParam> wsParams = new ArrayList<WsParam>();
			wsParams.add(new WsParam("pXmlIn", xml));
			wsParams.add(new WsParam("pType", "ORDER"));
			
			// 调用服务端 推送数据
			String request_url = infConfigurationMapper.getByKey(CONFIG_REQUEST_URL);
			String responseXml = WsClientUtil.axisInvokeWs(request_url, WS_NAMESPACE, WS_METHOD, wsParams);
			System.out.println(responseXml);
			//logger.debug(InfConstant.logInfo.LOG_RESPONSE + responseXml);
			responseXml = parseReponseXml(responseXml);
			if (StringUtils.isNotBlank(responseXml)) {
				@SuppressWarnings("unchecked")
				List<EbsArTrxFrb2cHResponse> response = XmlTranslateUtil.xmlToPOJO(responseXml, List.class, new HashMap<String,Class<?>>());
				List<String> successIdList= parseSuccessIds(response);
				try {
					// 修改发送成功标志
					if(CollectionUtils.isNotEmpty(successIdList)){
						orderReturnHeaderMapper.updateSendStatus(successIdList);
					}
				} catch (Throwable e) {
					logger.error(e.getMessage(),e);
					e.printStackTrace();
					
					InfExceptionLog log = new InfExceptionLog();
					log.setCreateTime(new Date());
					log.setExId(successIdList.toString());
					log.setExMsg(e.getMessage());
					log.setExDescription("修改发送成功标志出错");
					log.setInfCode(InfConstant.InfCodes.INF_EBS_ORDER_RETURN_F_CODE);
					infExceptionLogMapper.save(log);
				}
				try {
					// 修改报文发送次数, 发送一次 累加一次, 发送次数作为发送优先级
					orderReturnHeaderMapper.updateSendTimesAdd(headerIds);
				} catch (Throwable e) {
					logger.error(e.getMessage(),e);
					e.printStackTrace();
					
					InfExceptionLog log = new InfExceptionLog();
					log.setCreateTime(new Date());
					log.setExId(headerIds.toString());
					log.setExMsg(e.getMessage());
					log.setExDescription("修改发送次数出错");
					log.setInfCode(InfConstant.InfCodes.INF_EBS_ORDER_RETURN_F_CODE);
					infExceptionLogMapper.save(log);
				}
			}
		}
	}
	
	private List<EbsCuxArTrxFrb2cH> copyToVo(List<OrderReturnHeader> headers){
		List<EbsCuxArTrxFrb2cH> voList = new ArrayList<EbsCuxArTrxFrb2cH>();
		for(OrderReturnHeader header: headers){
			EbsCuxArTrxFrb2cH vo = new EbsCuxArTrxFrb2cH();
			voList.add(vo);
			
			vo.setPAY_METHOD(header.getPayMethod());//支付方式（支付宝、微信……）
			vo.setPAY_TYPE(header.getPayType());//支付类型（线上、线下）
			vo.setBUSINESS_TYPE("客退申请单");
			vo.setGL_DATE(DateFormatUtil.formatDate(new Date()));//总账日期、退货日期
			vo.setTRX_DATE(DateFormatUtil.formatDate(new Date()));//事物处理日期、退货日期
			vo.setORDER_ID(header.getOrderId());
			vo.setORDER_NUM(header.getOrderSn());
			vo.setORG_ID(header.getId());
			vo.setTRX_ID(header.getId().toString());//事务处理ID
			
			if(CollectionUtils.isNotEmpty(header.getDetails())){
				
				List<EbsCuxArTrxFrb2cL> lineList = new ArrayList<EbsCuxArTrxFrb2cL>();
				vo.setLINES(lineList);
				
				Double headerTotalPrice = 0d;
				for(int i=0; i<header.getDetails().size(); i++){
					OrderReturnDetail detail = header.getDetails().get(i);
					
					EbsCuxArTrxFrb2cL line = new EbsCuxArTrxFrb2cL();
					lineList.add(line);
					line.setTRX_ID(header.getId());//事务处理ID、需要跟headerId保持一致
					line.setLINE_NUM(i+1);
					
					line.setAMOUNT(detail.getTotalPrice().intValue());
					line.setDESCRIPTION("客退申请单明细");
					//line.setDiscount();//扣点
					line.setITEM_NO(detail.getSkuCode());
					line.setQTY(detail.getQty());//数量
					line.setSKU_QTY(detail.getQty());//SKU数量
					line.setORDER_ID(header.getOrderId());
					line.setORDER_NUM(header.getOrderSn());
					line.setPO_NUMBER(detail.getPoNo());
					line.setPRICE(detail.getUnitPrice().intValue());
					line.setSKU_CODE(detail.getSkuCode());
					line.setSKU_NAME(detail.getSkuName());
					line.setSKU_PRICE(detail.getUnitPrice().intValue());
					line.setSUPPLIER_ID(detail.getSupplierId());
					
					headerTotalPrice += detail.getTotalPrice();
				}
				vo.setAMOUNT(headerTotalPrice.intValue());//金额
				vo.setNET_AMOUNT(headerTotalPrice.intValue());
			}
		}
		
		return voList;
	}
	
	/**
	 * 转换成本地对象
	 * @param responseXml
	 * @return
	 */
	private String parseReponseXml(String responseXml){
		
		if(StringUtils.isNotBlank(responseXml)){
			responseXml = responseXml.replaceAll("<LINE>", "<me.quxiu.orderReturn.dto.EbsArTrxFrb2cLResponse>");
			responseXml = responseXml.replaceAll("</LINE>", "</me.quxiu.orderReturn.dto.EbsArTrxFrb2cLResponse>");
			responseXml = responseXml.replaceAll("<HEADER>", "<me.quxiu.orderReturn.dto.EbsArTrxFrb2cHResponse>");
			responseXml = responseXml.replaceAll("</HEADER>", "</me.quxiu.orderReturn.dto.EbsArTrxFrb2cHResponse>");
			
			return responseXml;
		}
		return null;
	}
	
	private List<String> parseSuccessIds(List<EbsArTrxFrb2cHResponse> responseList){
		List<String> idList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(responseList)){
			for(EbsArTrxFrb2cHResponse response: responseList){
				if(RESPONSE_SUCCESS_FLAG.equals(response.getHEADER_PROCESS_FLAG()) 
						&& StringUtils.isNotBlank(response.getTRX_ID())){
					idList.add(response.getTRX_ID()+StringUtils.EMPTY);
				}
			}
		}
		return idList;
	}
	
}

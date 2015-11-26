package me.quxiu.orderReturn.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.quxiu.orderReturn.inf.dto.EbsArTrxFrb2cHResponse;
import me.quxiu.orderReturn.inf.dto.EbsCuxArTrxFrb2cH;
import me.quxiu.orderReturn.inf.dto.EbsCuxArTrxFrb2cL;
import me.quxiu.orderReturn.inf.util.WsClientUtil;
import me.quxiu.orderReturn.inf.util.WsParam;
import me.quxiu.orderReturn.inf.util.XmlTranslateUtil;
import me.quxiu.orderReturn.mapper.OrderReturnDetailMapper;
import me.quxiu.orderReturn.mapper.OrderReturnHeaderMapper;
import me.quxiu.orderReturn.model.OrderReturnDetail;
import me.quxiu.orderReturn.model.OrderReturnHeader;
import me.quxiu.orderReturn.util.DateFormatUtil;
import me.quxiu.orderReturn.util.JsonXmToStringlUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年10月21日 下午4:40:38
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/spring-context.xml")
public class JobInfEbsOrderReturnFServiceTest {
	
	@Autowired
	private OrderReturnHeaderMapper orderReturnHeaderMapper;
	@Autowired
	private OrderReturnDetailMapper orderReturnDetailMapper;
	
	@Test
	public void saveTransmit(){
		List<OrderReturnHeader> headers = orderReturnHeaderMapper.getUnSendHeadersBySendTimes(10,10);
		if(!headers.isEmpty()){
			/*for(OrderReturnHeader header : headers){
				List<OrderReturnDetail> details = orderReturnDetailMapper.getByOrderReturnHeaderId(header.getId());
				header.setDetails(details);
			}*/
			
			List<EbsCuxArTrxFrb2cH> headerVOs = copyToVo(headers);
			
			Class<?> requestAliasHeader = EbsCuxArTrxFrb2cH.class;
			Class<?> requestAliasLine = EbsCuxArTrxFrb2cL.class;
			
			String xml = XmlTranslateUtil.POJOToXML(headerVOs, requestAliasHeader,requestAliasLine);
			JsonXmToStringlUtils.printObject(xml);
			List<WsParam> wsParams = new ArrayList<WsParam>();
			wsParams.add(new WsParam("pXmlIn", xml));
			wsParams.add(new WsParam("pType", "ORDER"));
			
			String request_url = "http://192.168.60.72:8888/qxalltoebs/qxalltoebsSoapHttpPort";
			String WS_NAMESPACE = "http://qxdbtest/Qxalltoebs.wsdl";
			String WS_METHOD = "allWsdataToebs";
			String responseXml = WsClientUtil.axisInvokeWs(request_url, WS_NAMESPACE, WS_METHOD, wsParams);
			JsonXmToStringlUtils.printObject(responseXml);
		}
	}
	
	
	public void reponse(){
		String responseXml = "<HEADERS><HEADER><TRX_ID>2</TRX_ID><LINES><LINE><LINE_NUM>3</LINE_NUM><ITEM_NO>sku0001</ITEM_NO>"
				+ "<LINE_PROCESS_FLAG>S</LINE_PROCESS_FLAG><LINE_ERR_MSG></LINE_ERR_MSG></LINE></LINES><HEADER_PROCESS_FLAG>S</HEADER_PROCESS_FLAG>"
				+ "<HEADER_ERR_MSG></HEADER_ERR_MSG></HEADER>"
				+"<HEADER><TRX_ID>2</TRX_ID><LINES><LINE><LINE_NUM>3</LINE_NUM><ITEM_NO>sku0001</ITEM_NO>"
				+ "<LINE_PROCESS_FLAG>S</LINE_PROCESS_FLAG><LINE_ERR_MSG></LINE_ERR_MSG></LINE></LINES><HEADER_PROCESS_FLAG>S</HEADER_PROCESS_FLAG>"
				+ "<HEADER_ERR_MSG></HEADER_ERR_MSG></HEADER></HEADERS>";
		
		responseXml = responseXml.replaceAll("<LINE>", "<me.quxiu.orderReturn.dto.EbsArTrxFrb2cLResponse>");
		responseXml = responseXml.replaceAll("</LINE>", "</me.quxiu.orderReturn.dto.EbsArTrxFrb2cLResponse>");
		
		responseXml = responseXml.replaceAll("<HEADER>", "<me.quxiu.orderReturn.dto.EbsArTrxFrb2cHResponse>");
		responseXml = responseXml.replaceAll("</HEADER>", "</me.quxiu.orderReturn.dto.EbsArTrxFrb2cHResponse>");
		
		Map<String,Class<?>> responseAlias = new HashMap<String,Class<?>>();
		
		List<EbsArTrxFrb2cHResponse> response = (List<EbsArTrxFrb2cHResponse>)XmlTranslateUtil.xmlToPOJO(responseXml, List.class, responseAlias);
		
		
		JsonXmToStringlUtils.printObject(response);
		 
		 
		/*try {
			JAXBContext context = JAXBContext.newInstance(EbsArTrxFrb2cHResponse.class);
	        Unmarshaller unmarshaller = context.createUnmarshaller();  
	        EbsArTrxFrb2cHResponse response = (EbsArTrxFrb2cHResponse)unmarshaller.unmarshal(new StringReader(responseXml)); 
	        TestJsonXmlUtils.printObject(response);
		} catch (JAXBException e) {
			e.printStackTrace();
		} */ 
		
	}
	
	private List<EbsCuxArTrxFrb2cH> copyToVo(List<OrderReturnHeader> headers){
		List<EbsCuxArTrxFrb2cH> voList = new ArrayList<EbsCuxArTrxFrb2cH>();
		for(OrderReturnHeader header: headers){
			
			EbsCuxArTrxFrb2cH vo = new EbsCuxArTrxFrb2cH();
			voList.add(vo);
			
			//vo.setAmount(header.get);
			vo.setBUSINESS_TYPE("客退申请单");
			vo.setGL_DATE(DateFormatUtil.formatDate(new Date()));//退货日期
			//vo.setNet_amount(net_amount);
			vo.setORDER_ID(header.getOrderId());
			vo.setORDER_NUM(header.getOrderSn());
			vo.setORG_ID(header.getId());
			vo.setPAY_METHOD(header.getPayMethod());
			vo.setPAY_TYPE(header.getPayType());
			vo.setTRX_DATE(DateFormatUtil.formatDate(new Date()));//退货日期
			vo.setTRX_ID(header.getId().toString());
			
			if(!header.getDetails().isEmpty()){
				
				List<EbsCuxArTrxFrb2cL> lineList = new ArrayList<EbsCuxArTrxFrb2cL>();
				vo.setLINES(lineList);
				
				Double headerTotalPrice = 0d;
				for(OrderReturnDetail detail : header.getDetails()){
					
					EbsCuxArTrxFrb2cL line = new EbsCuxArTrxFrb2cL();
					lineList.add(line);
					
					line.setAMOUNT(detail.getTotalPrice().intValue());
					line.setDESCRIPTION("客退申请单明细");
					//line.setDiscount(discount);
					line.setITEM_NO(detail.getSkuCode());
					line.setLINE_NUM(detail.getId());
					line.setORDER_ID(header.getOrderId());
					line.setORDER_NUM(header.getOrderSn());
					line.setPO_NUMBER(detail.getPoNo());
					line.setPRICE(detail.getUnitPrice().intValue());
					//line.setQty(detail.getQty());
					line.setSKU_CODE(detail.getSkuCode());
					line.setSKU_NAME(detail.getSkuName());
					line.setSKU_QTY(detail.getQty());
					line.setSKU_PRICE(detail.getUnitPrice().intValue());
					line.setSUPPLIER_ID(detail.getSupplierId());
					line.setTRX_ID(detail.getId());
					headerTotalPrice += detail.getTotalPrice();
				}
				vo.setAMOUNT(headerTotalPrice.intValue());
				vo.setNET_AMOUNT(headerTotalPrice.intValue());
			}
		}
		return voList;
	}
	
}

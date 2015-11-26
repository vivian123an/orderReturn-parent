package me.quxiu.orderReturn.inf.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAP11Constants;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

/**
 * WebService服务调用工具类
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年10月21日 下午3:59:26
 */
public class WsClientUtil {
	
	/**
	 * @deprecated 通过最原始的http POST方式调用webservice
	 * @param endpointUrl
	 * @param nameSpace
	 * @param methodName
	 * @param params
	 * @return
	 */
	public static String httpInvokeWs(String endpointUrl, String nameSpace, String methodName, List<WsParam> params) throws InfNetworkException {
		String result;
		try {
			String soapActionString = nameSpace + "/" + methodName;
			StringBuffer paramXml = new StringBuffer("");
			for (WsParam param : params) {
				paramXml.append("<").append(param.getParamName()).append(">");
				paramXml.append(param.getParamValue());
				paramXml.append("</").append(param.getParamName()).append(">");
			}
			Logger.getLogger(WsClientUtil.class).debug("http调用webservice的参数:" + paramXml);
			String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><" 
					+ methodName + " xmlns=\"" + nameSpace + "\">" + paramXml + "</" + methodName+ "></soapenv:Body></soapenv:Envelope>";
			if(log.isDebugEnabled())
			{
				log.debug("http调用webservice的SOAP消息:"+soap);
			}
			URL url = new URL(endpointUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			;
			httpConn.setRequestProperty("Content-Length", String.valueOf(soap.getBytes("UTF-8").length));
			httpConn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
			httpConn.setRequestProperty("soapActionString", soapActionString);
			httpConn.setRequestMethod("POST");
			httpConn.setReadTimeout(InfConstant.HTTP_TIMEOUT);
			httpConn.setConnectTimeout(InfConstant.HTTP_TIMEOUT);
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			OutputStream out = httpConn.getOutputStream();
			out.write(soap.getBytes("UTF-8"));
			out.close();

			InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), "UTF-8");

			BufferedReader in = new BufferedReader(isr);
			result = new String("");
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				result += inputLine;
			}
			in.close();
		} catch (Throwable e) {
			log.error(e.getMessage(),e);
			e.printStackTrace();
			throw new InfNetworkException("WebService服务链路异常:" + e.getMessage(), e);
		}
		return result;
	}

	public static String axisInvokeWs(String endpointUrl, String nameSpace, String methodName, List<WsParam> params) throws InfNetworkException {
		try {
			ServiceClient sc = new ServiceClient();
			Options opts = sc.getOptions();
			opts.setTo(new EndpointReference(endpointUrl));
			opts.setAction(nameSpace + "/" + methodName);
			opts.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
			opts.setProperty(HTTPConstants.CHUNKED, false);
			opts.setProperty(HTTPConstants.MC_ACCEPT_GZIP, Boolean.TRUE);
			opts.setTransportInProtocol("SOAP");
			opts.setTimeOutInMilliSeconds(InfConstant.HTTP_TIMEOUT);
			OMFactory fac = OMAbstractFactory.getOMFactory();
			OMNamespace omNs = fac.createOMNamespace(nameSpace, "tns");
			OMElement method = fac.createOMElement(methodName, omNs);
			for (WsParam p : params) {
				OMElement param = fac.createOMElement(p.getParamName(), omNs);
				param.setText(p.getParamValue());
				method.addChild(param);
			}
			OMElement res = sc.sendReceive(method);
			//当服务端不给我们客户端返回时，用到此判断
			Iterator<OMElement> iterator = res.getChildElements();
			if(!iterator.hasNext()){
				return null;
			}
			String content = null;
			while (iterator.hasNext()) {
				OMElement result = (OMElement) iterator.next();
				if(result.getLocalName().equals("pXmlOut_out")){
					content = result.getText();
				}
	        }
			sc.cleanupTransport();
			return content;
		} catch (Throwable e) {
			log.error(e.getMessage(),e);
			e.printStackTrace();
			throw new InfNetworkException("WebService服务链路异常:" + e.getMessage(), e);
		}
	}
	public static String axisInvoke_tms(String endpointUrl, String nameSpace, String methodName, List<WsParam> params) throws InfNetworkException {
		try {
			ServiceClient sc = new ServiceClient();
			Options opts = sc.getOptions();
			opts.setTo(new EndpointReference(endpointUrl));
			opts.setAction(nameSpace + "/" + methodName);
			//TODO delete
//			opts.setAction(methodName);
			opts.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
			opts.setProperty(HTTPConstants.CHUNKED, false);
			opts.setProperty(HTTPConstants.MC_ACCEPT_GZIP, Boolean.TRUE);
			opts.setTransportInProtocol("SOAP");
			opts.setTimeOutInMilliSeconds(InfConstant.HTTP_TIMEOUT);
			OMFactory fac = OMAbstractFactory.getOMFactory();
			OMNamespace omNs = fac.createOMNamespace(nameSpace, "tns");
			OMElement method = fac.createOMElement(methodName, omNs);
			for (WsParam p : params) {
				OMElement param = fac.createOMElement(p.getParamName(), omNs);
				param.setText(p.getParamValue());
				method.addChild(param);
			}
			OMElement res = sc.sendReceive(method);
			//当服务端不给我们客户端返回时，用到此判断
			if (res.getFirstElement() == null) {
				return null;
			}
			String content = res.getFirstElement().getText();
			sc.cleanupTransport();
			return content;
		} catch (Throwable e) {
			e.printStackTrace();
			throw new InfNetworkException("WebService服务链路异常:" + e.getMessage(), e);
		}
	}

	/**
	 * @deprecated 用axis2调用远程WEB服务，推荐使用，因为不需要解析WSDL文档
	 * 
	 * @param endpointUrl
	 *            WEB服务末端URL
	 * @param nameSpace
	 *            WEB服务命名空间
	 * @param methodName
	 *            方法名
	 * @param parms
	 *            要传的参数数组
	 * @return 对方响应内容
	 * @throws AxisFault
	 */
	public static String invokeWs(String endpointUrl, String nameSpace, String methodName, Map<String, Object> parms) throws InfNetworkException {
		try {
			ServiceClient sc = new ServiceClient();
			Options opts = sc.getOptions();
			opts.setTo(new EndpointReference(endpointUrl));
			opts.setAction("urn:" + methodName);
			opts.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
			opts.setProperty(HTTPConstants.CHUNKED, false);
			opts.setProperty(HTTPConstants.MC_ACCEPT_GZIP, Boolean.TRUE);
			opts.setTransportInProtocol("SOAP");
			opts.setTimeOutInMilliSeconds(InfConstant.HTTP_TIMEOUT);
			OMFactory fac = OMAbstractFactory.getOMFactory();
			OMNamespace omNs = fac.createOMNamespace(nameSpace, "tns");
			OMElement method = fac.createOMElement(methodName, omNs);
			for (String key : parms.keySet()) {
				OMElement param = fac.createOMElement(key, omNs);
				param.setText(String.valueOf(parms.get(key)));
				method.addChild(param);
			}
			OMElement res = sc.sendReceive(method);
			String content = res.getFirstElement().getText();
			sc.cleanupTransport();
			return content;
		} catch (Throwable e) {
			e.printStackTrace();
			throw new InfNetworkException("WebService服务链路异常:" + e.getMessage(), e);
		}
	}

	/**
	 * 
	 * 
	 * @param wsdlUrl
	 *            wsdl文档地址
	 * @param opName
	 *            方法名
	 * @param opArgs
	 *            参数
	 * @return 字符串
	 * @throws Exception
	 */
	private static Log log=LogFactory.getLog(WsClientUtil.class); 
	public static String invokeWs(String wsdlUrl, String opName, Object... opArgs) throws InfNetworkException {
		if(log.isDebugEnabled()){
			log.debug("WsClientUtil.invokeWs() 参数: wsdlUrl="+ wsdlUrl+" opName="+opName+" opArgs= "+ Arrays.toString(opArgs));
		}
		Object[] results = null;
		try {
			/*Client client = new Client(new URL(wsdlUrl));
			client.setTimeout(InfConstant.HTTP_TIMEOUT);
			client.setProperty(CommonsHttpMessageSender.GZIP_RESPONSE_ENABLED, Boolean.TRUE);// 告诉对方支持返回GZIP内容
			results = client.invoke(opName, opArgs);*/

		} catch (Throwable e) {
			log.error(e.getMessage(),e);
			throw new InfNetworkException("WebService服务链路异常:" + e.getMessage(), e);
		}

		return (String) results[0];
	}

}
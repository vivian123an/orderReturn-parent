package me.quxiu.orderReturn.inf.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

/**
 * HTTP请求客户端工具类
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年10月21日 下午3:59:26
 */
public class HttpClientUtil {
	private static HttpClient httpClient;

	private HttpClientUtil() {
	}

	private final static HttpClient getInstance() {
		if (httpClient == null) {
			MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
			HttpConnectionManagerParams params = new HttpConnectionManagerParams();
			params.setConnectionTimeout(InfConstant.HTTP_TIMEOUT);
			params.setSoTimeout(InfConstant.HTTP_TIMEOUT);
			params.setMaxTotalConnections(10000);
			params.setDefaultMaxConnectionsPerHost(1000);
			connectionManager.setParams(params);
			httpClient = new HttpClient(connectionManager);
			httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, true));
		}
		return httpClient;
	}

	/**
	 * 通过HTTP协议请求
	 * 
	 * @param url
	 *            不能为空
	 * @param parameters
	 *            可为空
	 * @return 目标地址响应内容
	 * @throws TmsHttpTransmitException
	 *             网络异常
	 */
	public static String getRequest_ResponseBodyAsString(String url, Map<String, String> parameters) throws InfNetworkException {
		if(url==null)
        {
        	Logger.getLogger(HttpClientUtil.class).warn(" the url is null");
        	return "";
        }
		String params = parameterToString(parameters, "utf-8");
		Logger.getLogger(HttpClientUtil.class).debug("HttpGet请求:" + url + (params != null ? url.contains("?") ? "&" + params : "?" + params : ""));
		GetMethod method = new GetMethod(url + (params != null ? url.contains("?") ? "&" + params : "?" + params : ""));
		int statusCode;
		try {
			// method.addRequestHeader("Accept-Encoding", "gzip, deflate");
			statusCode = HttpClientUtil.getInstance().executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
				throw new InfNetworkException("请求的服务器有异常:服务器状态码为" + statusCode);
			}
			if (method.getResponseHeader("Content-Encoding") != null) {
				String acceptEncoding = method.getResponseHeader("Content-Encoding").getValue();
				if (acceptEncoding.toLowerCase().indexOf("gzip") > -1) {
					// 建立gzip解压工作流
					StringBuffer sb = new StringBuffer();
					InputStream is = method.getResponseBodyAsStream();
					GZIPInputStream gzin = new GZIPInputStream(is);
					InputStreamReader isr = new InputStreamReader(gzin); // 设置读取流的编码格式
																			// ，
																			// 自定义编码
					java.io.BufferedReader br = new java.io.BufferedReader(isr);
					String tempbf;
					while ((tempbf = br.readLine()) != null) {
						sb.append(tempbf);
						sb.append("\r\n");
					}
					isr.close();
					gzin.close();
					return sb.toString();
				}

			}
			return method.getResponseBodyAsString();
		} catch (Throwable e) {
			throw new InfNetworkException("Http服务链路异常:" + e.getMessage(), e);
		} finally {
			method.releaseConnection();
		}
	}

	/**
	 * 通过HTTP协议请求
	 * 
	 * @param url
	 * @param parameters
	 * @return 字节
	 * @throws InfNetworkException
	 *             网络异常
	 */
	public static byte[] getRequest_ResponseBodyAsBytes(String url, Map<String, String> parameters) throws InfNetworkException {
		String params = parameterToString(parameters, "utf-8");
		Logger.getLogger(HttpClientUtil.class).debug("HttpGet请求:" + url + (params != null ? url.contains("?") ? "&" + params : "?" + params : ""));
		GetMethod method = new GetMethod(url + (params != null ? url.contains("?") ? "&" + params : "?" + params : ""));
		int statusCode;
		try {
			// method.setRequestHeader("Accept-Encoding", "gzip, deflate");
			statusCode = httpClient.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
				throw new InfNetworkException("Http服务链路异常:服务器状态码为" + statusCode);
			}
			return method.getResponseBody();
		} catch (Throwable e) {
			throw new InfNetworkException("Http服务链路异常:" + e.getMessage(), e);
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
	}

	/**
	 * 通过HTTP协议请求
	 * 
	 * @param url
	 * @param parameters
	 * @return 流对象
	 * @throws InfNetworkException
	 *             网络异常
	 */
	public static InputStream getRequest_ResponseBodyAsStream(String url, Map<String, String> parameters) throws InfNetworkException {
		String params = parameterToString(parameters, "utf-8");
		Logger.getLogger(HttpClientUtil.class).debug("HttpGet请求:" + url + (params != null ? url.contains("?") ? "&" + params : "?" + params : ""));
		GetMethod method = new GetMethod(url + (params != null ? url.contains("?") ? "&" + params : "?" + params : ""));
		int statusCode;
		try {
			// method.setRequestHeader("Accept-Encoding", "gzip, deflate");
			statusCode = httpClient.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
				throw new InfNetworkException("Http服务链路异常:服务器状态码为" + statusCode);
			}
			return method.getResponseBodyAsStream();
		} catch (Throwable e) {
			throw new InfNetworkException("Http服务链路异常:" + e.getMessage(), e);
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
	}
	public static String streamRequest_ResponseBodyAsStringB2C(String url, String param) throws InfNetworkException {
		if(url==null)
        {
        	Logger.getLogger(HttpClientUtil.class).warn(" the url is null");
        	return "";
        }
		Logger.getLogger(HttpClientUtil.class).debug("Http写流请求:" + url + (param != null ? url.contains("?") ? "&" + param : "?" + param : ""));
		OutputStream out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
//			conn.setRequestProperty("accept", "*/*");
//			conn.setRequestProperty("Content-Length", "" + Integer.toString(param.getBytes().length));
//			conn.setRequestProperty("Content-Type", "text/xml;charset=utf8");
//			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf8");
			conn.setRequestMethod("POST");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			//conn.setReadTimeout(60000);
			conn.setConnectTimeout(60000);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = conn.getOutputStream();
			// 发送请求参数
			out.write(param.getBytes("UTF-8"));
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
			conn.disconnect();
		} catch (Throwable e) {
			throw new InfNetworkException("Http服务链路异常:" + e.getMessage(), e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public static String streamRequest_ResponseBodyAsString(String url, String param) throws InfNetworkException {
		if(url==null)
        {
        	Logger.getLogger(HttpClientUtil.class).warn(" the url is null");
        	return "";
        }
		Logger.getLogger(HttpClientUtil.class).debug("Http写流请求:" + url + (param != null ? url.contains("?") ? "&" + param : "?" + param : ""));
		OutputStream out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("Content-Length", "" + Integer.toString(param.getBytes().length));
			conn.setRequestProperty("Content-Type", "text/xml;charset=utf8");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestMethod("POST");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setReadTimeout(60000);
			conn.setConnectTimeout(60000);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = conn.getOutputStream();
			// 发送请求参数
			out.write(param.getBytes("UTF-8"));
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
			conn.disconnect();
		} catch (Throwable e) {
			throw new InfNetworkException("Http服务链路异常:" + e.getMessage(), e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String postRequest_ResponseBodyAsString(String url, Map<String, String> parameters) throws InfNetworkException {
		if(url==null)
        {
        	Logger.getLogger(HttpClientUtil.class).warn(" the url is null");
        	return "";
        }
		Logger.getLogger(HttpClientUtil.class).debug("HttpPost请求:" + url + (parameters != null ? url.contains("?") ? "&" + parameters : "?" + parameters : ""));

		PostMethod method = new PostMethod(url);
		int statusCode;
		try {
			for (String key : parameters.keySet()) {
				method.addParameter(key, parameters.get(key));
			}
			method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			statusCode = HttpClientUtil.getInstance().executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
				throw new InfNetworkException("Http服务链路异常:服务器状态码为" + statusCode);
			}
			return method.getResponseBodyAsString();
		} catch (Throwable e) {
			throw new InfNetworkException("Http服务链路异常:" + e.getMessage(), e);
		} finally {
			method.releaseConnection();
		}
	}

	/**
	 * 以POST方式发送请求
	 * 
	 * @param url
	 * @param parameters
	 * @return 返回字节内容
	 * @throws InfNetworkException
	 */
	public static byte[] postRequest_ResponseBodyAsBytes(String url, Map<String, String> parameters) throws InfNetworkException {
		Logger.getLogger(HttpClientUtil.class).debug("HttpPost请求:" + url + (parameters != null ? url.contains("?") ? "&" + parameters : "?" + parameters : ""));
		PostMethod method = new PostMethod(url);
		int statusCode;
		try {
			for (String key : parameters.keySet()) {
				method.addParameter(key, parameters.get(key));
			}
			method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			// method.setRequestHeader("Accept-Encoding", "gzip, deflate");
			statusCode = httpClient.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
				throw new InfNetworkException("Http服务链路异常:服务器状态码为" + statusCode);
			}
			return method.getResponseBody();
		} catch (Throwable e) {
			throw new InfNetworkException("Http服务链路异常:" + e.getMessage(), e);
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
	}

	/**
	 * 以POST方式发送请求
	 * 
	 * @param url
	 * @param parameters
	 * @return 返回流
	 * @throws InfNetworkException
	 */
	public static InputStream postRequest_ResponseBodyAsStream(String url, Map<String, String> parameters) throws InfNetworkException {
		Logger.getLogger(HttpClientUtil.class).debug("HttpPost请求:" + url + (parameters != null ? url.contains("?") ? "&" + parameters : "?" + parameters : ""));
		PostMethod method = new PostMethod(url);
		int statusCode;
		try {
			for (String key : parameters.keySet()) {
				method.addParameter(key, parameters.get(key));
			}
			method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			// method.setRequestHeader("Accept-Encoding", "gzip, deflate");
			statusCode = httpClient.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
				throw new InfNetworkException("Http服务链路异常:服务器状态码为" + statusCode);
			}
			return method.getResponseBodyAsStream();
		} catch (Throwable e) {
			throw new InfNetworkException("Http服务链路异常:" + e.getMessage(), e);
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
	}

	// 对参数编码组合成一个get请求参数串
	private static String parameterToString(Map<String, String> parameters, String input_charset) {
		if (parameters != null) {
			StringBuffer result = new StringBuffer();
			List<String> keys = new ArrayList<String>(parameters.keySet());
			for (int i = 0; i < keys.size(); i++) {
				try {
					String key = (String) keys.get(i);
					String value = (String) parameters.get(key);
					if (i == keys.size() - 1) {
						result.append(key).append("=").append(URLEncoder.encode(value, input_charset));
					} else {
						result.append(key).append("=").append(URLEncoder.encode(value, input_charset)).append("&");
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			return result.toString();
		}
		return null;
	}
}

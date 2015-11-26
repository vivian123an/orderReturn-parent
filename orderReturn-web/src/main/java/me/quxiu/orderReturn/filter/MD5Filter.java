package me.quxiu.orderReturn.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.quxiu.orderReturn.util.Md5Util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 继承OncePerRequestFilter保证一次请求只过滤一次(以兼容不同的servlet container)
 * 
 * md5 加密串  uri + SECRET_KEY + tokenTime
 * 
 * @author wenan.chen@quxiu.me
 * 
 */
public class MD5Filter extends OncePerRequestFilter{
	
	public static final String SECRET_KEY = "quxiu2015api";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//AB9945D5E0AA11922191EF8F206C7679
		
		String tokenTime = request.getParameter("tokenTime");//time stamp;
		String token = request.getParameter("token");
		String uri = request.getRequestURI();
		
		if(StringUtils.isBlank(token) || StringUtils.isBlank(tokenTime) || StringUtils.isBlank(uri)){
			
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write("{ \"statusCode\" : 200,\"responseBody\" : {\"responseInfo\" : {\"reasons\" : {\"code\" : \"400\",\"type\" : 0, \"msg\" : \"非法请求\"}},\"data\" : null,\"query\" : null}}");
		}else{
			if(token.equals(Md5Util.encodeByMD5(uri + SECRET_KEY + tokenTime))){
				
			    long diffMillSecond = System.currentTimeMillis() - Long.valueOf(tokenTime);
				
				if(diffMillSecond < 30000){
					filterChain.doFilter(request, response);
				}else{
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().write("{ \"statusCode\" : 200,\"responseBody\" : {\"responseInfo\" : {\"reasons\" : {\"code\" : \"400\",\"type\" : 0, \"msg\" : \"非法请求\"}},\"data\" : null,\"query\" : null}}");

				}
			}else{
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().write("{ \"statusCode\" : 200,\"responseBody\" : {\"responseInfo\" : {\"reasons\" : {\"code\" : \"400\",\"type\" : 0, \"msg\" : \"非法请求\"}},\"data\" : null,\"query\" : null}}");
			}
		}
		
	}


	public static void main(String[] args){
		long timeMill1 = System.currentTimeMillis();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long timeMill2 = System.currentTimeMillis();
		
		System.out.println(timeMill1);
		System.out.println(timeMill2 - timeMill1);
		
		
	}

}
package me.quxiu.orderReturn.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年9月17日 下午5:40:20
 * 
 */

public class DateFormatUtil {

	//考虑到SimpleDateFormat非线程安全
	private static final ThreadLocal<DateFormat> THREAD_LOCAL_DATEFORMAT = new ThreadLocal<DateFormat>() {
	    protected DateFormat initialValue() {
	        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    }
	};
	
	private static final ThreadLocal<DateFormat> THREAD_LOCAL_DATE = new ThreadLocal<DateFormat>() {
	    protected DateFormat initialValue() {
	        return new SimpleDateFormat("yyyy-MM-dd");
	    }
	}; 
	
	/**
	 * 字符串转日期，如果格式错误返回null<br>
	 * 
	 * 格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateTimeString
	 * @return
	 */
	public static Date parseDateTime(String dateTimeString){
		try {
			return THREAD_LOCAL_DATEFORMAT.get().parse(dateTimeString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 日期转字符串<br>
	 * 
	 * 返回格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date){
		return THREAD_LOCAL_DATEFORMAT.get().format(date);
	}
	
	/**
	 * 日期转字符串<br>
	 * 
	 * 返回格式：yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date){
		return THREAD_LOCAL_DATE.get().format(date);
	}
	
}

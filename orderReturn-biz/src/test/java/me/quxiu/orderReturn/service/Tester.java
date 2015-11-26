package me.quxiu.orderReturn.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年8月14日 下午5:16:53
 * 
 */

public class Tester {

	public static void main1(String[] args) throws ParseException {
		/*int date = (int)(new Date().getTime()/1000);
		System.out.println(date);*/
		
		int value = 1431509431;

		int year = value * 1000 / 10000;
		int month = (value * 1000 % 10000) / 100;
		int day = value * 1000 % 100;
		Date date = new GregorianCalendar(year, month, day).getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(date));
	}
	
	public static void main2(String[] args) {
		Integer[] numbers = new Integer[]{1,2,3,4};
		String result = "";
		for(Integer number: numbers){
			result += ","+number;
		}
		System.out.println(result.substring(1));
	}
	
	public static void main3(String[] args){
        List<String> alphaList = new ArrayList<String>();
        alphaList.add("A");
        alphaList.add("B");
        alphaList.add("C");
        alphaList.add("A");
        alphaList.add("B");
        System.out.println("List values .....");
        for (String alpha : alphaList)
        {
            System.out.println(alpha);
        }
        Set<String> alphaSet = new HashSet<String>(alphaList);
        System.out.println("\nSet values .....");
        for (String alpha : alphaSet)
        {
            System.out.println(alpha);
        }
    }
	
	public static void main(String[] args) {
		List<String> alphaList = new ArrayList<String>();
        alphaList.add("A");
        alphaList.add("B");
        alphaList.add("C");
        
        for(String str : alphaList){
        	System.out.println(str);
        }
	}
}

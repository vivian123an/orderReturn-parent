package me.quxiu.orderReturn.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import me.quxiu.orderReturn.inf.dto.EbsCuxArRecFrb2c;
import me.quxiu.orderReturn.inf.dto.EbsCuxArRecFrb2cItem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * @author min.wang@quxiu.me
 * @version 2015年10月21日 上午11:08:03
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/spring-context.xml")
public class EbsFeedBackRefundStatusServiceTest {

	@Resource
	private  EbsFeedBackRefundStatusService ebsFeedBackRefundStatusService;
	
	
	@Test
	public void testUpdateReFundStatus(){
		
		StringBuffer str= new StringBuffer();
		str.append("<root>");
			str.append("<item>");
					str.append("<id>1110</id>");
					str.append("<refund_id>28</refund_id>");
					str.append("<order_return_header_id>7</order_return_header_id>");
					str.append("<order_id>68376</order_id>");
					str.append("<order_status>200</order_status>");
					str.append("<memo>备注</memo>");
					str.append("<refund_date>2015-11-5 14:30:31</refund_date>");
					str.append("</item>");
			
			str.append("</root>");
		String str2 = "<root><item><id>11</id>"
				+ "<refund_id>11</refund_id><order_id>1234</order_id><order_status>001</order_status><memo>shuoming</memo><refund_date>2015-05-09 12:25:00</refund_date></item></root>";
		try {
			String result = ebsFeedBackRefundStatusService.updateReFundStatus(str.toString());
			System.out.println(result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test
	public void testXstream01(){

			
			EbsCuxArRecFrb2c e = new EbsCuxArRecFrb2c();
			
			List<EbsCuxArRecFrb2cItem> items = new ArrayList<EbsCuxArRecFrb2cItem>();
			EbsCuxArRecFrb2cItem item = new EbsCuxArRecFrb2cItem();
			item.setAMOUNT(10.22);
			items.add(item);
			EbsCuxArRecFrb2cItem item2 = new EbsCuxArRecFrb2cItem();
			item2.setAMOUNT(10.23);
			items.add(item2);
			e.setItems(items);
			
			 XStream xs = new XStream(new StaxDriver(new NoNameCoder()));
		     xs.alias("HEADERS", EbsCuxArRecFrb2c.class);
	         xs.alias("HEADER", EbsCuxArRecFrb2cItem.class);
	         xs.addImplicitCollection(EbsCuxArRecFrb2c.class, "items");
	         
	         System.out.println(xs.toXML(e));
			
		
	}
	
	
	
}

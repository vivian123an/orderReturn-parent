package me.quxiu.orderReturn.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import me.quxiu.orderReturn.model.InfEbsOrderRefund;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/spring-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InfEbsOrderRefundMapperTest {
	
	

	@Autowired
	InfEbsOrderRefundMapper infEbsOrderRefundMapper;
	
	@Test
	public void testGet(){
		
		InfEbsOrderRefund obj = infEbsOrderRefundMapper.get(1);
		System.out.println(obj);
	}
	
	@Test
	public void testUpdateOrderRefundStatus(){
		
		
		Map params = new HashMap();
		params.put("id", 1);
		params.put("refund_date", "2014-11-18 14:30:31");
		int result = infEbsOrderRefundMapper.updateOrderRefundStatus(params);
		System.out.println(result);
		
	}
	
	@Test
	public void testUpdateOrderAfterSaleById(){
		
		
		Map params = new HashMap();
		params.put("order_id", 68275);
		params.put("order_status", "1");
		int result = infEbsOrderRefundMapper.updateOrderAfterSaleById(params);
		System.out.println(result);
		
	}

}

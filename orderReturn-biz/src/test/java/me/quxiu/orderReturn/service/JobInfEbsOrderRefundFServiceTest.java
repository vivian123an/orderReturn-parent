package me.quxiu.orderReturn.service;

import me.quxiu.orderReturn.inf.ebs.service.JobInfEbsOrderRefundFService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/spring-context.xml")
public class JobInfEbsOrderRefundFServiceTest {
	
	@Autowired
	JobInfEbsOrderRefundFService jobInfEbsOrderRefundFService;
	
	
	@Test
	public void test001(){
		
		
		jobInfEbsOrderRefundFService.saveTransmit();
		
	}

}

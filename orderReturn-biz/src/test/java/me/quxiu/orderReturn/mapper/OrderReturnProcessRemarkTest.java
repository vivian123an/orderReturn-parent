package me.quxiu.orderReturn.mapper;

import java.util.List;

import me.quxiu.orderReturn.model.OrderReturnProcessRemark;

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
public class OrderReturnProcessRemarkTest {
	
	@Autowired
	OrderReturnProcessRemarkMapper mapper;
	
	
	@Test
	public void getRemarksByReturnNo(){
		List<OrderReturnProcessRemark> remarks = mapper.getRemarksByReturnNo("201511040001");
		System.out.println(remarks);
		
	
	
	}
	public static void main(String[] args) {
		
		String url = "public/return/201511/20/474076_1448011447200.JPG|public/return/201511/20/47test76_144802221447200.JPG";
		String[] urls = url.split("\\|");
		for(String _url:urls){
			System.out.println(_url);
		}
		
	}
	
	

}

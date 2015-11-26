package me.quxiu.orderReturn.mapper;

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
public class OrderReturnDetailMapperTest {
	
	@Autowired
	OrderReturnDetailMapper mapper;
	
	
	@Test
	public void getRemarksByReturnNo(){
		try {
			Object result = mapper.findServiceByGoodsId(53246);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}

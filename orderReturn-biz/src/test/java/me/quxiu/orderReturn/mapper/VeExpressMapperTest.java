package me.quxiu.orderReturn.mapper;

import me.quxiu.orderReturn.mapper.VeExpressMapper;
import me.quxiu.orderReturn.util.JsonXmToStringlUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年11月6日 下午5:17:17
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/spring-context.xml")
public class VeExpressMapperTest {

	@Autowired
	private VeExpressMapper veExpressMapper;
	
	@Test
	public void test(){
		JsonXmToStringlUtils.printObject(veExpressMapper.getSimpleExpressAll());
	}
}

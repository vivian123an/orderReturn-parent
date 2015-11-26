package me.quxiu.orderReturn.mapper;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年10月22日 下午5:53:06
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/spring-context.xml")
public class InfConfigurationMapperTest {

	@Resource
	private InfConfigurationMapper infConfigurationMapper;
	
	@Test
	public void getByKey(){
		System.out.println(infConfigurationMapper.getByKey("B2C_EBS_001_REQUEST_URL"));
		System.out.println(infConfigurationMapper.getByKey("B2C_EBS_001_REQUSET_MAX_NUM"));
	}
	
	
}

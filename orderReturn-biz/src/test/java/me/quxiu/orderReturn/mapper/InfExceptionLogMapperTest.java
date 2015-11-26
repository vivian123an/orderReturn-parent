package me.quxiu.orderReturn.mapper;

import java.util.Date;

import javax.annotation.Resource;

import me.quxiu.orderReturn.model.InfExceptionLog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年10月22日 下午5:53:45
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/spring-context.xml")
public class InfExceptionLogMapperTest {

	@Resource
	private InfExceptionLogMapper infExceptionLogMapper;
	
	@Test
	public void save(){
		
		InfExceptionLog log = new InfExceptionLog();
		log.setCreateTime(new Date());
		log.setExId("654321");
		log.setExMsg("error");
		log.setExDescription("修改数据出错");
		log.setInfCode("B2C_EBS_001");
		infExceptionLogMapper.save(log);
		
	}
	
}

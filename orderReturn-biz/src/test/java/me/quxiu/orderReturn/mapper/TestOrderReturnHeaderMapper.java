package me.quxiu.orderReturn.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import me.quxiu.orderReturn.model.OrderReturnHeader;
import me.quxiu.orderReturn.util.JsonXmToStringlUtils;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/spring-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOrderReturnHeaderMapper  {


	@Resource
	OrderReturnHeaderMapper orderReturnHeaderMapper;

	@Test
	public void save(){
		List<OrderReturnHeader> list = orderReturnHeaderMapper.getUnSendHeadersBySendTimes(10, 10);
		JsonXmToStringlUtils.printObject(list);
	}
	
	@Test
	public void testUpdateRefundStatusByReturnNo(){
		
		String statusBms="299";
		String processUser="474076";
		String returnNo="1447255851420";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("status", statusBms);
		params.put("statusBms", statusBms);
		params.put("returnNo", returnNo);
		params.put("processUser", processUser);
		String msg = null;
		if(OrderReturnHeader.STATUS_CANCEL.equals(statusBms)){
			msg = OrderReturnHeader.STATUS_CANCEL_MSG;
			
		}else if(OrderReturnHeader.STATUS_REFUNDING.equals(statusBms)){
			msg =  OrderReturnHeader.STATUS_REFUNDING_MSG;
		}
		params.put("statusMsg", msg);
		
		orderReturnHeaderMapper.updateRefundStatusByReturnNo(params);
	}
	
}

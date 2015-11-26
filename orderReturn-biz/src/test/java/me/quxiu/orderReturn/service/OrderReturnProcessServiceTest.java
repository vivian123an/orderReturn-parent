package me.quxiu.orderReturn.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import me.quxiu.orderReturn.dto.ExpressInfoDto;
import me.quxiu.orderReturn.dto.OrderItemDto;
import me.quxiu.orderReturn.dto.ReturnApplyDto;
import me.quxiu.orderReturn.util.JsonXmToStringlUtils;
import me.quxiu.share.result.ResultEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年11月6日 下午6:18:09
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/spring-context.xml")
public class OrderReturnProcessServiceTest {

	@Resource
	private OrderReturnProcessService orderReturnProcessService;
	
	public void getReturnReasonList(){
		JsonXmToStringlUtils.printObject(orderReturnProcessService.getReturnReasonList());
	}
	
	public void getOrderItemList(){
		JsonXmToStringlUtils.printObject(orderReturnProcessService.getOrderItemList(68434));
	}
	
	
	public void getProcessLogs(){
		JsonXmToStringlUtils.printObject(orderReturnProcessService.getProcessLogs("1447063046655"));
	}
	
	public void updateTransportNo(){
		ExpressInfoDto queryDto = new ExpressInfoDto();
		queryDto.setTransportNo("12345678");
		queryDto.setExpressName("顺丰快递");
		queryDto.setExpressCode("shunfeng");
		queryDto.setReturnNo("1447063046655");
		ResultEntity<Object> result = orderReturnProcessService.updateTransportNo(queryDto);
		JsonXmToStringlUtils.printObject(result);
	}
	
	@Test
	public void saveReturnApply(){
		ReturnApplyDto applyDto = new ReturnApplyDto();
		
		List<OrderItemDto> details = new ArrayList<OrderItemDto>();
		OrderItemDto item = new OrderItemDto();
		item.setQty(2);
		item.setReasonCode(3);
		item.setSkuCode("6310053");
		item.setSkuName("宝娜斯  单面加档丝袜连裤袜 透气打底袜  灰色（6双装） [均码]");
		item.setUnitPrice(69.00);
		item.setTotalPrice(138.0000);
		details.add(item);
		 
		/*OrderItemDto item2 = new OrderItemDto();
		item2.setQty(3);
		item2.setReasonCode(3);
		item2.setSkuCode("211180104035");
		item2.setSkuName("阿尔皮纳  袋鼠男士性感轻薄三角裤  单条装21118 花灰 [XXL]");
		item2.setUnitPrice(19.00);
		item2.setTotalPrice(57.00);
		details.add(item2);*/
		
		applyDto.setImgUrl1("http://img1.gtimg.com/14/1421/142127/14212779_980x1200_0.jpg");
		applyDto.setImgUrl2("http://img1.gtimg.com/14/1421/142127/14212779_980x1200_0.jpg");
		applyDto.setImgUrl3("http://img1.gtimg.com/14/1421/142127/14212779_980x1200_0.jpg");
		applyDto.setOrderId(68452);
		applyDto.setOrderSn("2015092210265519");
		applyDto.setReturnDescript("退货原因描述，心情不好，所以退货");
		applyDto.setUserId(474097);
		applyDto.setDetails(details);
		
		JsonXmToStringlUtils.printObject(orderReturnProcessService.saveReturnApply(applyDto));
	}
	
	
	@Test
	public void testUpdateTransportNo(){
		
		ExpressInfoDto expressInfo = new ExpressInfoDto();
		expressInfo.setExpressCode("Fedex");
		expressInfo.setExpressName("联邦快递");
		expressInfo.setReturnNo("1447213338232");
		expressInfo.setTransportNo("TEST2015112012");
		orderReturnProcessService.updateTransportNo(expressInfo);
		
		
	}
	
	
}

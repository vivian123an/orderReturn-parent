package me.quxiu.orderReturn.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import me.quxiu.orderReturn.dto.OrderReturnHeaderDto;
import me.quxiu.orderReturn.dto.OrderReturnProcessRemarkDto;
import me.quxiu.orderReturn.mapper.OrderReturnDetailMapper;
import me.quxiu.orderReturn.model.OrderReturnDetail;
import me.quxiu.orderReturn.model.OrderReturnHeader;
import me.quxiu.orderReturn.query.OrderReturnHeaderQuery;
import me.quxiu.orderReturn.util.JsonXmToStringlUtils;
import me.quxiu.orderReturn.util.UDFQxEncrypt;
import me.quxiu.share.result.ResultEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年10月21日 上午11:08:03
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/spring-context.xml")
public class OrderReturnHeaderServiceTest {

	@Resource
	private OrderReturnHeaderService orderReturnHeaderService;
	@Resource
	private OrderReturnDetailMapper orderReturnDetailMapper;
	
	@Test
	public void saveTest(){
		OrderReturnHeader header = new OrderReturnHeader();
		header.setContactPhoneNo("18665686291");
		header.setContactUser("vivian");
		header.setImgUrl1("http://img4.imgtn.bdimg.com/it/u=1071041737,3060155175&fm=21&gp=0.jpg");
		header.setImgUrl2("http://img4.imgtn.bdimg.com/it/u=1071041737,3060155175&fm=21&gp=0.jpg");
		header.setImgUrl3("http://img4.imgtn.bdimg.com/it/u=1071041737,3060155175&fm=21&gp=0.jpg");
		header.setMemo("测试备注");
		header.setOrderId(68275);
		header.setOrderSn("2015062712282143");
		header.setPayMethod("1");
		header.setPayType("2");
		header.setReturnDescript("不是自己想要的款式");
				

		header.setUserId(474070);
		
		List<OrderReturnDetail> details = new ArrayList<OrderReturnDetail>();
		OrderReturnDetail detail = new OrderReturnDetail();
		detail.setMemo("申请单明细备注1");
		detail.setPoNo("123456");
		detail.setQty(1);
		detail.setSkuCode("sku0001");
		detail.setSkuName("纯色内衣大码");
		detail.setSkuUnit("件");
		detail.setSupplierId(100001);
		detail.setTotalPrice(128d);
		detail.setUnitPrice(128d);
		//detail.setReturnDescript("不是自己想要的款式");
		detail.setReturnReason(1);
		details.add(detail);
		
		OrderReturnDetail detail2 = new OrderReturnDetail();
		detail2.setMemo("申请单明细备注2");
		detail2.setPoNo("654321");
		detail2.setQty(2);
		detail2.setSkuCode("sku0002");
		detail2.setSkuName("白色内衣中码");
		detail2.setSkuUnit("件");
		detail2.setSupplierId(100001);
		detail2.setTotalPrice(96d);
		detail2.setUnitPrice(48d);
		//detail2.setReturnDescript("不是自己想要的款式");
		detail.setReturnReason(1);
		details.add(detail2);
		
		header.setDetails(details);
		
		JsonXmToStringlUtils.printObject(header);
		ResultEntity<Object> result = orderReturnHeaderService.saveOrderReturn(header);
		JsonXmToStringlUtils.printObject(result);
		
	}
	
	
	public void updateTransportNo(){
		
		ResultEntity<Object> result = orderReturnHeaderService.updateTransportNo(2, "12345678");
		JsonXmToStringlUtils.printObject(result);
	}
	
	
	public void getList(){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("query", new OrderReturnHeaderQuery());
		JsonXmToStringlUtils.printObject(orderReturnHeaderService.getOrderReturnHeaderPage(params));
	}
	
	@Test
	public void testGetOrderHeaderListForBms(){
		
		
		Map<String,Object> params = new HashMap<String,Object>();
		OrderReturnHeaderQuery query = new OrderReturnHeaderQuery();
		query.setContactPhoneNo("18565156421");
//		query.setCreateTimeStart("2015-11-04 17:00:00");
//		query.setStatusBms("400");
		query.setPageSize(100);
		params.put("query", query);
		System.out.println(UDFQxEncrypt.qxEncrypt("18565156421"));
		
		ResultEntity<OrderReturnHeaderDto>  result = orderReturnHeaderService.getOrderReturnHeaderBmsPage(params);
//		ResultEntity<OrderReturnHeaderDetailDto> detail = orderReturnHeaderService.getOrderReturnDetailForBms(Long.valueOf(34));
//		System.out.println(detail);
		System.out.println(result);
		
	}
	/**
	 * 更新客退单状态为取消或发起退款
	 */
	@Test
	public void testUpdateOrderReturnHeaderStatus(){
		
		
		
		
		
		OrderReturnHeaderDto orderReturnHeaderDto = new OrderReturnHeaderDto();
		orderReturnHeaderDto.setReturnNo("1447063046655");
		orderReturnHeaderDto.setStatusBms(OrderReturnHeader.STATUS_CANCEL);
		 orderReturnHeaderService.updateOrderReturnHeaderStatus(orderReturnHeaderDto);
		
		
	}
	
	@Test
	public void testSaveProcessRemark(){
		OrderReturnProcessRemarkDto processRemarkDto = new OrderReturnProcessRemarkDto();
		processRemarkDto.setProcessUser("test");
		processRemarkDto.setRemarkMsg("testest");
		processRemarkDto.setReturnNo("201511040001");
		try {
			ResultEntity<OrderReturnProcessRemarkDto> result= orderReturnHeaderService.savePrcessRemark(processRemarkDto);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	 
	
}
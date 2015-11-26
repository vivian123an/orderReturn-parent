package me.quxiu.orderReturn.mapper;

import java.util.Date;

import me.quxiu.orderReturn.model.OrderReturnExpressCoupon;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wenan.chen@quxiu.me
 * @version 2015年11月17日 下午5:30:44
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/spring-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderReturnExpressCouponMapperTest {

	@Autowired
	OrderReturnExpressCouponMapper orderReturnExpressCouponMapper;
	
	@Test
	public void saveTest(){
		OrderReturnExpressCoupon coupon = new OrderReturnExpressCoupon();
		coupon.setCreateBy("test");
		coupon.setCreateTime(new Date());
		coupon.setIsDelete(false);
		coupon.setIsMakeup(false);
		coupon.setIsSendCoupon(false);
		coupon.setMakeupMoney(12.00f);
		coupon.setOrderId(68452);
		coupon.setOrderSn("2015092210265519");
		coupon.setReturnNo("1447751954156");
		coupon.setUpdateTime(new Date());
		coupon.setUpdateBy("test");
		coupon.setSupplierId(1313);
		orderReturnExpressCouponMapper.save(coupon);
	}
}

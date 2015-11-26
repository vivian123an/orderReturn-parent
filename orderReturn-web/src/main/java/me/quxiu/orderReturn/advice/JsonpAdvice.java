package me.quxiu.orderReturn.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * 需要使用jsonp返回，统一放到me.quxiu.orderReturn.controller.jsonp包名称下面
 * 
 * <p>basePackages = me.quxiu.orderReturn.controller.jsonp</p>
 * 
 * @author wenan.chen@quxiu.me
 * @version 2015年11月9日 下午3:50:45
 * 
 */

@ControllerAdvice(basePackages = "me.quxiu.orderReturn.controller.jsonp")
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
	
    public JsonpAdvice() {
        super("callback", "jsonp");
    }
}

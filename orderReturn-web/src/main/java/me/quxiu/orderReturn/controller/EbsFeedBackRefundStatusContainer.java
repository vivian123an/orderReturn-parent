package me.quxiu.orderReturn.controller;

import me.quxiu.orderReturn.service.EbsFeedBackRefundStatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/orderReturn")
public class EbsFeedBackRefundStatusContainer {
	
	@Autowired
	EbsFeedBackRefundStatusService ebsFeedBackRefundStatusService;
	
	@ResponseBody
	@RequestMapping("/EBSFeedBackRefundStatus")
	public String eBSFeedBackRefundStatus(String xmlStr){
		
		
		return ebsFeedBackRefundStatusService.updateReFundStatus(xmlStr);
		
	}

}

package me.quxiu.orderReturn.controller.jsonp;

import java.util.HashMap;
import java.util.Map;

import me.quxiu.orderReturn.dto.OrderReturnHeaderDetailDto;
import me.quxiu.orderReturn.dto.OrderReturnHeaderDto;
import me.quxiu.orderReturn.dto.OrderReturnProcessRemarkDto;
import me.quxiu.orderReturn.model.OrderReturnHeader;
import me.quxiu.orderReturn.query.OrderReturnHeaderQuery;
import me.quxiu.orderReturn.service.OrderReturnHeaderService;
import me.quxiu.share.result.Reason;
import me.quxiu.share.result.ResultEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
* @ClassName: OrderReturnHeaderController 
* @Description: TODO() 
* @author min.wang@quxiu.me
* @date 2015年11月18日 下午6:52:12 
*
 */
@Controller
@RequestMapping("/orderReturn")
public class OrderReturnHeaderController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OrderReturnHeaderService orderReturnHeaderService;
	
	@ResponseBody
	@RequestMapping("/applytest")
	public ResultEntity<Object> orderReturnApply(OrderReturnHeader dto){
		ResultEntity<Object> result = new ResultEntity<Object>();
		
		try {
			return orderReturnHeaderService.saveOrderReturn(dto);
		}  catch (Exception e) {
			result.setReasonCode(Reason.REASON_CODE_SERVER_ERROR);
			result.setReasonMsg("客退申请失败,orderReturnIdko");
			logger.error(e.getMessage());
		}
		
		return result;
		
	}
	
	/**
	 * 更新运单信息
	 * @param headerId
	 * @param transportNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/kuaidi/{headerId}/{transportNo}")
	public ResultEntity<Object> updateTransportNo(@PathVariable("headerId") Integer headerId,
			@PathVariable("transportNo") String transportNo){
		ResultEntity<Object> result = new ResultEntity<Object>();

		try {
			return orderReturnHeaderService.updateTransportNo(headerId, transportNo);
		} catch (Exception e) {
			result.setReasonCode(Reason.REASON_CODE_SERVER_ERROR);
			result.setReasonMsg("客退申请,更新运单号失败,headerId:"+headerId);
			logger.error(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 新增客服备注信息
	 * @param processRemarkDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addRemarkInfo")
	public ResultEntity<OrderReturnProcessRemarkDto> addRemarkInfo(OrderReturnProcessRemarkDto processRemarkDto){
		
		
		return orderReturnHeaderService.savePrcessRemark(processRemarkDto);
		
	}
	
	/**
	 * 客退单列表查询
	 * @param query
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listForBms")
	public ResultEntity<OrderReturnHeaderDto> listForBms(OrderReturnHeaderQuery query){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("query", query);
		return orderReturnHeaderService.getOrderReturnHeaderBmsPage(params);
	}
	
	/**
	 * 查询客退单详情
	 * @param headerId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/refundDetail/{headerId}")
	public ResultEntity<OrderReturnHeaderDetailDto> getOrderReturnDetailForBms(@PathVariable("headerId") Long headerId){
		
		return orderReturnHeaderService.getOrderReturnDetailForBms(headerId);
		
	}
	
	/**
	 * 更新客退状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateOrderReturnHeaderStatus")
	public ResultEntity<OrderReturnHeader> updateOrderReturnHeaderStatus(OrderReturnHeaderDto orderReturnHeaderDto){
		
		return orderReturnHeaderService.updateOrderReturnHeaderStatus(orderReturnHeaderDto);
		
	}
	
}

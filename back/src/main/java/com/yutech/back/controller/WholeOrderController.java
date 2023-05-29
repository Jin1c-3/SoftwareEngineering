package com.yutech.back.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yutech.back.common.exception.GlobalException;
import com.yutech.back.common.utils.Result;
import com.yutech.back.entity.po.FlightTicket;
import com.yutech.back.entity.po.TrainTicket;
import com.yutech.back.entity.po.WholeOrder;
import com.yutech.back.service.persistence.FlightTicketService;
import com.yutech.back.service.persistence.TrainTicketService;
import com.yutech.back.service.persistence.WholeOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-03
 */
@RestController
@RequestMapping("/whole-order")
@Slf4j
@Api(tags = "订单接口")
@CrossOrigin
public class WholeOrderController {

	@Autowired
	private WholeOrderService wholeOrderService;

	@Autowired
	private FlightTicketService flightTicketService;

	@Autowired
	private TrainTicketService trainTicketService;

	/**
	 * 根据用户id查询订单
	 *
	 * @param usrId 用户id
	 * @return 订单列表
	 */
	@GetMapping("/getOrder")
	@ApiImplicitParam(name = "usrId", value = "用户id", required = true, dataTypeClass = String.class)
	@ApiOperation(value = "根据用户id查询订单", notes = "根据用户id查询订单")
	public Result<List<WholeOrder>> getOrderByUsrId(String usrId) {
		log.debug("查询订单信息=======" + usrId);
		return Result.ok(wholeOrderService.list(new QueryWrapper<WholeOrder>().eq("usr_id", usrId))).message("查询成功");
	}

	@PostMapping("/callback")
//	@ApiOperation(value = "支付宝回调", notes = "支付宝回调")
	public String callback(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			log.debug("参数>>" + name + ":" + valueStr);
			params.put(name, valueStr);
		}
		requestParams.get("trade_status");

		String outTradeNo = request.getParameter("out_trade_no");
		String tradeStatus = request.getParameter("trade_status");
		String subject = request.getParameter("subject");
		String sellerId = request.getParameter("seller_id");
		String vehicleType = subject.substring(0, 2);
		//支付宝流水
		String tradeNo = request.getParameter("trade_no");
		log.info("trade_status>>" + tradeStatus + ">>trade_no>>" + tradeNo + ">>out_trade_no>>" + outTradeNo);
		if ("TRADE_FINISHED".equals(tradeStatus) || "TRADE_SUCCESS".equals(tradeStatus) || tradeStatus == null) {
			//支付成功，修改订单状态
			log.debug("支付宝回调订单号===" + outTradeNo);
			WholeOrder wholeOrder = wholeOrderService.getOne(new QueryWrapper<WholeOrder>().eq("order_id", outTradeNo));
			wholeOrder.setOrderStatus("是");
			log.debug("支付宝回调后更新订单信息===" + wholeOrder);
			wholeOrderService.updateById(wholeOrder);

			if ("飞机".equals(vehicleType)) {
				try {
					List<FlightTicket> flightTicketList = flightTicketService.list(new QueryWrapper<FlightTicket>().eq("order_id", outTradeNo));
					for (FlightTicket flightTicket : flightTicketList) {
						flightTicket.setTicketStatus("未值机");
						flightTicketService.updateById(flightTicket);
					}
				} catch (Exception e) {
					throw new GlobalException("机票状态更改失败", e);
				}
			} else if ("火车".equals(vehicleType)) {
				try {
					List<TrainTicket> trainTicketList = trainTicketService.list(new QueryWrapper<TrainTicket>().eq("order_id", outTradeNo));
					for (TrainTicket trainTicket : trainTicketList) {
						trainTicket.setTicketStatus("未检票");
						trainTicketService.updateById(trainTicket);
					}
				} catch (Exception e) {
					throw new GlobalException("火车票状态更改失败", e);
				}
			} else {
				throw new GlobalException("交通工具类型捕捉出错，" + vehicleType + "不存在");
			}
			return "success";
		} else {
			return "fail";
		}
	}
}


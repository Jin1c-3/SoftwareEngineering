package com.yutech.back.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yutech.back.common.utils.Result;
import com.yutech.back.entity.po.WholeOrder;
import com.yutech.back.service.persistence.WholeOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
@Api(tags = "订单管理")
@CrossOrigin
public class WholeOrderController {
	@Autowired
	private WholeOrderService wholeOrderService;

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
	@ApiOperation(value = "支付宝回调", notes = "支付宝回调")
	public String callback(HttpServletRequest request) {
		if (request.getParameter("trade_status").equals("TRADE_SUCCESS") || request.getParameter("trade_status").equals("TRADE_FINISHED")) {
			//支付成功，修改订单状态
			String out_trade_no = request.getParameter("out_trade_no");
			log.debug("支付宝回调订单号===" + out_trade_no);
			WholeOrder wholeOrder = wholeOrderService.getOne(new QueryWrapper<WholeOrder>().eq("order_id", out_trade_no));
			wholeOrder.setOrderFlag("是");
			log.debug("支付宝回调后更新订单信息===" + wholeOrder);
			wholeOrderService.updateById(wholeOrder);
			return "Success";
		} else {
			return "Fail";
		}
	}
}


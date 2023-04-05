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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@ApiImplicitParam(name = "usrId", value = "用户id", required = true, dataType = "Passenger对象")
	@ApiOperation(value = "根据用户id查询订单", notes = "根据用户id查询订单")
	public Result<List<WholeOrder>> getOrderByUsrId(String usrId) {
		log.debug("查询订单信息=======" + usrId);
		return Result.ok(wholeOrderService.list(new QueryWrapper<WholeOrder>().eq("usr_id", usrId)));
	}
}


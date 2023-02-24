package com.yutech.back.controller;

import com.yutech.back.common.exception.GlobalException;
import com.yutech.back.common.utils.ExceptionUtil;
import com.yutech.back.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api(tags = "测试页面")
public class TestController {
	/**
	 * 用于测试 异常 是否能被统一处理，并返回指定格式的数据
	 *
	 * @return
	 */
	@GetMapping("/testGlobalException")
	public Result testGlobalException() {
		try {
			int test = 10 / 0;
		} catch (Exception e) {
			throw new GlobalException(ExceptionUtil.getMessage(e));
		}
		return Result.ok();
	}

	/**
	 * 访问项目的 swagger-ui.html 页面，即可访问到 Swagger 页面。
	 * 比如：http://localhost:8080/swagger-ui.html，
	 * 只有标注了 @ApiOperation 注解的接口才会被显示在 接口文档中。
	 */
	@ApiOperation(value = "测试 Swagger")
	@GetMapping("/testSwagger")
	public Result testSwagger() {
		return Result.ok();
	}
}

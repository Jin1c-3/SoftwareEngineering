package com.yutech.back.controller;

import com.yutech.back.common.exception.GlobalException;
import com.yutech.back.common.utils.ExceptionUtil;
import com.yutech.back.common.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
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
}

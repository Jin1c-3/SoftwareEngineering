package com.yutech.back.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yutech.back.common.exception.GlobalException;
import com.yutech.back.common.utils.ExceptionUtil;
import com.yutech.back.common.utils.Result;
import com.yutech.back.common.validator.group.AddGruop;
import com.yutech.back.common.validator.group.UpdateGruop;
import com.yutech.back.entity.User;
import com.yutech.back.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

	/**
	 * 对于实现分页，首先声明一个 Page 对象，并指定查询的当前页以及每页的大小，
	 * 然后直接调用 MyBatisPlus 提供的 page 方法即可，page 方法中可以添加过滤条件。
	 */
	@Autowired
	private UserService userService;

	@ApiOperation(value = "测试分页插件")
	@GetMapping("/testMyBatisPlus/page/{current}/{size}")
	public Result testPage(@PathVariable("current") Long current, @PathVariable("size") Long size) {
		Page<User> page = new Page(current, size);
		return Result.ok().data("page", userService.page(page, null));
	}

	@ApiOperation(value = "测试 JSR 303 插入时的校验规则")
	@PostMapping("testValidator/save")
	public Result testValidatorSave(@Validated({AddGruop.class}) @RequestBody User user) {
		if(userService.save(user)) {
			return Result.ok().message("数据添加成功");
		}
		return Result.error().message("数据添加失败");
	}

	@ApiOperation(value = "测试 JSR 303 更新时的校验规则")
	@PostMapping("testValidator/update")
	public Result testValidatorUpdate(@Validated({UpdateGruop.class}) @RequestBody User user) {
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("username", user.getUsername());
		if(userService.update(user, updateWrapper)) {
			return Result.ok().message("数据更新成功");
		}
		return Result.error().message("数据更新失败");
	}
}
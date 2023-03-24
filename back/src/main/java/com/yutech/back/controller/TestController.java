package com.yutech.back.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import com.yutech.back.common.exception.GlobalException;
import com.yutech.back.common.utils.ExceptionUtil;
import com.yutech.back.common.utils.JwtUtil;
import com.yutech.back.common.utils.Result;
import com.yutech.back.common.validator.group.AddGroup;
import com.yutech.back.common.validator.group.UpdateGroup;
import com.yutech.back.entity.User;
import com.yutech.back.service.AlipayService;
import com.yutech.back.service.EMailSenderService;
import com.yutech.back.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/test")
@Api(tags = "测试页面")
@CrossOrigin
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
	 * 比如：<a href="http://localhost:8080/swagger-ui.html">...</a>，
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
		Page<User> page = new Page<>(current, size);
		return Result.ok().data("page", userService.page(page, null));
	}

//	@GetMapping("/testPageHelper/page/{current}/{size}")
//	public Result testPageHelper(@PathVariable("current") int current, @PathVariable("size") int size) {
//		PageHelper.startPage(current, size);
//		List<User> userList = userService.selectAll();
//		PageInfo<User> userPageInfo = new PageInfo<>(userList);
//		return Result.ok().data("page", userPageInfo.getPages());
//	}

	//RequestBody注解只能用于Post请求，不能用于Get。因为Get请求没有Body
	@ApiOperation(value = "测试 JSR 303 插入时的校验规则")
	@PostMapping("/testValidator/save")
	public Result testValidatorSave(@Validated({AddGroup.class}) @RequestBody User user) {
		if (userService.save(user)) {
			return Result.ok().message("数据添加成功");
		}
		return Result.error().message("数据添加失败");
	}

	@ApiOperation(value = "测试 JSR 303 更新时的校验规则")
	@PostMapping("/testValidator/update")
	public Result testValidatorUpdate(@Validated({UpdateGroup.class}) @RequestBody User user) {
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("username", user.getUsername());
		if (userService.update(user, updateWrapper)) {
			return Result.ok().message("数据更新成功");
		}
		return Result.error().message("数据更新失败");
	}

	@ApiOperation(value = "测试 token 验证的效果")
	@PostMapping("/testTokenValidation/login")
	public Result testValidateUserByToken(@RequestBody User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		String token = JwtUtil.sign(username, password);
		if (StringUtils.isNullOrEmpty(token)) {
			return Result.ok().message("用户token发放成功").data("token", token);
		}
		return Result.error().message("用户token验证失效");
	}

	@Autowired
	private EMailSenderService eMailSenderService;

	/**
	 * 测试通过QQ邮件发送验证码的基础功能
	 *
	 * @param to   发到哪儿去
	 * @param code 验证码是什么
	 * @return Result
	 */
	@ApiOperation(value = "测试通过QQ邮件发送验证码的基础功能")
	@RequestMapping("/testCodeMailing/{to}/{code}")
	public Result testCodeMailing(@PathVariable("to") String to, @PathVariable("code") String code) {
		eMailSenderService.sendCodeMail(to, code);
		return Result.ok();
	}

	@Autowired
	private AlipayService alipayService;

	@ApiOperation(value = "测试通过支付宝支付的基础功能")
	@RequestMapping("/testAlipay/{subject}/{money}")
	public String testAlipay(@PathVariable("subject") String subject, @PathVariable("money") BigDecimal money) {
		return alipayService.toPay(subject, money, null);
//		return Result.ok();
	}
}

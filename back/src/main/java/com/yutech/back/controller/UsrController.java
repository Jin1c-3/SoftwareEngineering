package com.yutech.back.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yutech.back.common.utils.JwtUtil;
import com.yutech.back.common.utils.Result;
import com.yutech.back.entity.po.Usr;
import com.yutech.back.service.persistence.UsrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-03
 */
@RestController
@RequestMapping("/usr")
@Api(tags = "用户管理")
public class UsrController {
	@Autowired
	private UsrService usrService;

	/**
	 * 默认头像
	 */
	private static final String DEFAULT_AVATAR = "https://www.bing.com/images/search?view=detailV2&ccid=BvuYHSYN&id=02C66B98F86BC00A151528157870BBB54FED69C7&thid=OIP.BvuYHSYNZjqGp5_j7pNfwwAAAA&mediaurl=https%3a%2f%2fhbimg.huabanimg.com%2f78a381109dd1919a0bde420b62525b2df37ac73f17b1-rx7BWq_fw658&exph=320&expw=320&q=%e9%bb%98%e8%ae%a4%e5%a4%b4%e5%83%8f&simid=608016616232333826&FORM=IRPRST&ck=46BE7D2781EC05527C3A0E7566A79A87&selectedIndex=121";

	/**
	 * 用户注册
	 *
	 * @param usr 用户信息
	 * @return Result
	 */
	@ApiOperation(value = "用户注册", notes = "用户注册，会检验唯一性")
	@PostMapping("/registry")
	public Result usrRegistry(@RequestBody Usr usr) {
		Format sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		//验证账号唯一性
		if (usrService.verifyUnique(usr)) {
			usr.setUsrId(sdf.format(new Date()) + UUID.randomUUID());
			//设置默认头像
			if (usr.getUsrAvatar() == null) usr.setUsrAvatar(DEFAULT_AVATAR);
			usrService.save(usr);
			Result.ok().data("token", JwtUtil.sign(usr.getUsrId(), usr.getUsrPwd()));
		}
		return Result.error().message("账号已存在");
	}

	/**
	 * 用户登录
	 *
	 * @param usr 用户信息
	 * @return Result
	 */
	@ApiOperation(value = "用户登录", notes = "用户登录")
	@GetMapping("/login")
	public Result usrLogin(Usr usr) {
		Usr usrInDB = usrService.getOne(new QueryWrapper<Usr>().eq("usr_account", usr.getUsrAccount()));
		if (usrInDB != null) {
			if (usrInDB.getUsrPwd().equals(usr.getUsrPwd())) {
				return Result.ok().data("token", JwtUtil.sign(usrInDB.getUsrId(), usrInDB.getUsrPwd()));
			}
		}
		return Result.error().message("账号或密码错误");
	}
}


package com.yutech.back.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yutech.back.common.utils.FileUtil;
import com.yutech.back.common.utils.JwtUtil;
import com.yutech.back.common.utils.Result;
import com.yutech.back.entity.dto.UsrDTO;
import com.yutech.back.entity.po.Usr;
import com.yutech.back.service.persistence.UsrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@Slf4j
@CrossOrigin
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
	 * @param usrDTO 用户信息
	 * @return Result
	 */
	@ApiOperation(value = "用户注册", notes = "用户注册，会检验唯一性。注意传头像的时候，他的key应该是avatar而不是UsrAvatar")
	@PostMapping("/registry")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "usr_account", value = "账号", required = true, dataType = "string"),
			@ApiImplicitParam(name = "usr_pwd", value = "密码", required = true, dataType = "string"),
			@ApiImplicitParam(name = "usr_email", value = "邮箱", required = true, dataType = "string"),
			@ApiImplicitParam(name = "usr_phone", value = "手机号", required = true, dataType = "string"),
			@ApiImplicitParam(name = "avatar", value = "头像", dataType = "MultipartFile对象")
	})
	public Result<UsrDTO> usrRegistry(@RequestBody UsrDTO usrDTO, HttpServletRequest request) {
		Usr usr = usrDTO.getUsr();
		Format sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		//验证账号唯一性
		if (usrService.verifyUnique(usr)) {
			usr.setUsrId(sdf.format(new Date()) + UUID.randomUUID());
			//如果用户没有上传头像，则使用默认头像
			if (usr.getUsrAvatar() == null) {
				usr.setUsrAvatar(DEFAULT_AVATAR);
			} else {
				usr.setUsrAvatar(FileUtil.storeMultipartFile(usr.getUsrId(), usrDTO.getAvatar(), request));
			}
			//保存用户信息
			usrService.save(usr);
			log.info("用户注册成功，用户id为{}", usr.getUsrId());
			return Result.ok(new UsrDTO(usr, JwtUtil.sign(usr.getUsrId(), usr.getUsrPwd())));
		}
		return Result.error(new UsrDTO(usr)).message("账号已存在");
	}

	/**
	 * 用户登录
	 *
	 * @param usr 用户信息
	 * @return Result
	 */
	@ApiOperation(value = "用户登录", notes = "用户登录，返回详细用户对象Usr以及token")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "usr_account", value = "账号", required = true, dataType = "string"),
			@ApiImplicitParam(name = "usr_pwd", value = "密码", required = true, dataType = "string")
	})
	@GetMapping("/login")
	public Result<UsrDTO> usrLogin(Usr usr) {
		Usr usrInDB = usrService.getOne(new QueryWrapper<Usr>().eq("usr_account", usr.getUsrAccount()));
		if (usrInDB != null) {
			if (usrInDB.getUsrPwd().equals(usr.getUsrPwd())) {
				log.info("用户登录成功，用户id为{}", usrInDB.getUsrId());
				return Result.ok(new UsrDTO(usr, JwtUtil.sign(usr.getUsrId(), usr.getUsrPwd())));
			}
			return Result.error(new UsrDTO(usr)).message("账号或密码错误");
		}
		return Result.error(new UsrDTO(usr)).message("账号不存在");
	}

	/**
	 * 更新用户信息
	 *
	 * @param usr 为了获取用户id
	 * @return Result
	 */
	@PatchMapping("/update")
	@ApiOperation(value = "修改用户信息", notes = "修改用户信息")
	public Result<Usr> updateUsrInfo(Usr usr) {
		if (usrService.verifyUnique(usr)) {
			usrService.updateById(usr);
			log.info("用户信息修改成功，用户id为{}", usr.getUsrId());
			return Result.ok(usr);
		}
		return Result.error(usr).message("账号冲突");
	}
}


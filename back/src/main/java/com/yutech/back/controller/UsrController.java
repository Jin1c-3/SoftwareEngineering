package com.yutech.back.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yutech.back.common.utils.FileUtil;
import com.yutech.back.common.utils.JwtUtil;
import com.yutech.back.common.utils.Result;
import com.yutech.back.entity.bo.dto.UsrDTO;
import com.yutech.back.entity.po.Usr;
import com.yutech.back.service.bussiness.AliSmsService;
import com.yutech.back.service.bussiness.EMailSenderService;
import com.yutech.back.service.persistence.UsrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

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

	@Autowired
	private EMailSenderService eMailSenderService;

	@Autowired
	private AliSmsService aliSmsService;

	/**
	 * 默认头像
	 */
	private static final String DEFAULT_AVATAR = "https://www.nitutu.com/uploads/allimg/200409/2136324362-1.jpeg";

	/**
	 * 用户注册
	 *
	 * @param usr 用户信息
	 * @return Result
	 */
	@ApiOperation(value = "用户注册", notes = "用户注册，会检验唯一性。注意传头像的时候，他的key应该是avatar而不是UsrAvatar")
	@PostMapping("/registry")
	public Result<Usr> usrRegistry(@RequestBody Usr usr) {
		log.debug("用户注册，前端信息：=======" + usr);
		Format sdf = new SimpleDateFormat("yyyyMMdd");
		//验证账号唯一性
		if (usrService.verifyUnique(usr)) {
			usr.setUsrId(sdf.format(new Date()) + "-" + UUID.randomUUID());
			usr.setUsrAvatar(DEFAULT_AVATAR);
			//保存用户信息
			usrService.save(usr);
			//发送欢迎邮件
			eMailSenderService.sendGreetingsMail(usr.getUsrAccount(), usr.getUsrAccount());
			log.info("用户注册成功，用户为{}", usr);
			return Result.ok(usr).message("注册成功");
		}
		log.debug("用户注册失败，可能违反唯一性，用户为======{}", usr);
		return Result.error(usr).message("账号已存在");
	}

	/**
	 * 用户登录
	 *
	 * @param usr 用户信息
	 * @return Result
	 */
	@ApiOperation(value = "用户登录", notes = "用户登录，返回详细用户对象Usr以及token")
	@GetMapping("/login")
	public Result<UsrDTO> usrLogin(Usr usr) {
		log.debug("用户登录，前端信息：=======" + usr);
		Usr usrInDB = usrService.getOne(new QueryWrapper<Usr>().eq("usr_account", usr.getUsrAccount()));
		if (usrInDB == null) {
			log.info("用户登录失败，账号不存在，用户为{}", usr);
			return Result.error(new UsrDTO(usr)).message("账号不存在");
		}
		if (!usrInDB.getUsrPwd().equals(usr.getUsrPwd())) {
			log.info("用户登录失败，密码错误，用户为{}", usr);
			return Result.error(new UsrDTO(usr)).message("账号或密码错误");
		}
		log.info("用户登录成功======{}", usrInDB);
		return Result.ok(new UsrDTO(usrInDB, JwtUtil.sign(usrInDB.getUsrId(), usrInDB.getUsrPwd())));
	}

	/**
	 * 修改用户信息
	 *
	 * @param usrDTO 用户信息
	 * @return Result
	 */
	@PatchMapping("/update")
	@ApiOperation(value = "修改用户信息", notes = "修改用户信息，请传入usrId!!!!!!")
	public Result<Usr> updateUsrInfo(UsrDTO usrDTO, HttpServletRequest request) {
		log.debug("用户信息修改，前端信息：=======" + usrDTO);
		Usr usr = usrDTO.getUsr();
		Usr usrInDB = usrService.getOne(new QueryWrapper<Usr>().eq("usr_ID", usr.getUsrId()));
		if (usrInDB == null) {
			return Result.error(usr).message("用户不存在");
		}
		if (!usrInDB.getUsrPwd().equals(usr.getUsrPwd())) {
			return Result.error(usr).message("密码错误");
		}
		if (usrDTO.getAvatar() != null) {
			usr.setUsrAvatar(FileUtil.storeMultipartFile(usrInDB.getUsrId(), usrDTO.getAvatar(), request));
		}
		//更新用户信息
		usrService.updateById(usrInDB);
		log.info("用户信息更新成功，用户为 {}", usrInDB);
		return Result.ok(usrInDB);
	}

	@GetMapping("/before-update/{phoneOrEMail}")
	@ApiOperation(value = "修改用户信息前的验证", notes = "修改用户信息前的验证，返回用户信息")
	@ApiParam(name = "accountOrPhone", value = "账号或手机号", required = true)
	public Result<String> beforeUpdate(@PathVariable String phoneOrEMail) {
		Boolean isEMail = phoneOrEMail.contains("@");
		log.debug("修改用户信息前的验证，前端信息：======{}======是否是邮箱：======{}======", phoneOrEMail, isEMail);
		String code = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
		if (isEMail) {
			eMailSenderService.sendCodeMail(phoneOrEMail, code);
		} else {
			//TODO 发送短信
			aliSmsService.sendSmsYZM(phoneOrEMail, code);
		}
		log.debug("返回前端的验证码：======{}", code);
		return Result.ok(code);
	}
}


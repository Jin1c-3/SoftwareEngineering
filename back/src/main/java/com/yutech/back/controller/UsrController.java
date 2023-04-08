package com.yutech.back.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yutech.back.common.utils.FileUtil;
import com.yutech.back.common.utils.JwtUtil;
import com.yutech.back.common.utils.Result;
import com.yutech.back.entity.dto.UsrDTO;
import com.yutech.back.entity.po.Usr;
import com.yutech.back.service.persistence.UsrService;
import io.swagger.annotations.Api;
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
	private static final String DEFAULT_AVATAR = "https://www.duitang.com/blog/?id=1416137961";

	/**
	 * 用户注册
	 *
	 * @param usrDTO 用户信息
	 * @return Result
	 */
	@ApiOperation(value = "用户注册", notes = "用户注册，会检验唯一性。注意传头像的时候，他的key应该是avatar而不是UsrAvatar")
	@PostMapping("/registry")
	public Result<UsrDTO> usrRegistry(@RequestBody UsrDTO usrDTO, HttpServletRequest request) {
		log.debug("用户注册，前端信息：=======" + usrDTO);
		Usr usr = usrDTO.getUsr();
		Format sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		//验证账号唯一性
		if (usrService.verifyUnique(usr)) {
			usr.setUsrId(sdf.format(new Date()) + UUID.randomUUID());
			//如果用户没有上传头像，则使用默认头像
			if (usrDTO.getAvatar() == null) {
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
	@GetMapping("/login")
	public Result<UsrDTO> usrLogin(Usr usr) {
		Usr usrInDB = usrService.getOne(new QueryWrapper<Usr>().eq("usr_account", usr.getUsrAccount()));
		if (usrInDB == null) {
			return Result.error(new UsrDTO(usr)).message("账号不存在");
		}
		if (!usrInDB.getUsrPwd().equals(usr.getUsrPwd())) {
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
	@ApiOperation(value = "修改用户信息", notes = "修改用户信息")
	public Result<Usr> updateUsrInfo(UsrDTO usrDTO, HttpServletRequest request) {
		log.debug("用户信息修改，前端信息：=======" + usrDTO);
		Usr usr = usrDTO.getUsr();
		Usr usrInDB = usrService.getOne(new QueryWrapper<Usr>().eq("usr_account", usr.getUsrAccount()));
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
}


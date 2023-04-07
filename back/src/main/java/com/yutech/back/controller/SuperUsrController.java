package com.yutech.back.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yutech.back.common.utils.Result;
import com.yutech.back.entity.dto.SuperUsrOper;
import com.yutech.back.entity.po.SuperUsr;
import com.yutech.back.service.persistence.SuperUsrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 管理员系统，注意这里不会被拦截器拦截
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-03
 */
@RestController
@RequestMapping("/super-usr")
@Api(tags = "管理员系统")
@CrossOrigin
@Slf4j
public class SuperUsrController {

	@Value("&{super.usr.id}")
	private String superUsrId;

	@Autowired
	private SuperUsrService superUsrService;

	/**
	 * 检测输入是否为超级管理员
	 *
	 * @param superUsr 管理员信息
	 * @return null表示是超级管理员
	 */
	private Result isRoot(SuperUsr superUsr) {
		if (!superUsr.getSuperUsrId().equals(superUsrId)) {
			log.info("管理员权限不足======" + superUsr.getSuperUsrId() + "======");
			return Result.error().message("权限不足");
		}
		SuperUsr superUsrInDB = superUsrService.getOne(new QueryWrapper<SuperUsr>().eq("super_usr_ID", superUsr.getSuperUsrId()));
		if (!superUsrInDB.getSuperUsrPwd().equals(superUsr.getSuperUsrPwd())) {
			log.info("管理员密码错误======" + superUsr.getSuperUsrId() + "======");
			return Result.error().message("密码错误");
		}
		return null;
	}

	/**
	 * 检测输入是否为超级管理员
	 *
	 * @param superUsr 管理员信息
	 * @param data     返回数据
	 * @param <T>      返回数据类型
	 * @return null表示是超级管理员
	 */
	private <T> Result<T> isRoot(SuperUsr superUsr, T data) {
		if (!superUsr.getSuperUsrId().equals(superUsrId)) {
			log.info("管理员权限不足======" + superUsr.getSuperUsrId() + "======");
			return Result.error(data).message("权限不足");
		}
		SuperUsr superUsrInDB = superUsrService.getOne(new QueryWrapper<SuperUsr>().eq("super_usr_ID", superUsr.getSuperUsrId()));
		if (!superUsrInDB.getSuperUsrPwd().equals(superUsr.getSuperUsrPwd())) {
			log.info("管理员密码错误======" + superUsr.getSuperUsrId() + "======");
			return Result.error(data).message("密码错误");
		}
		return null;
	}

	/**
	 * 管理员登录
	 *
	 * @param superUsr 管理员信息
	 * @return Result
	 */
	@ApiOperation(value = "管理员登录", notes = "管理员登录，只会验证账号密码，不发放token")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "superUsrId", value = "管理员账号", required = true, dataType = "string"),
			@ApiImplicitParam(name = "superUsrPwd", value = "管理员密码", required = true, dataType = "string")
	})
	@GetMapping("/login")
	public Result<SuperUsr> login(SuperUsr superUsr) {
		SuperUsr superUsrInDB = superUsrService.getOne(new QueryWrapper<SuperUsr>().eq("super_usr_ID", superUsr.getSuperUsrId()));
		if (superUsrInDB == null) {
			log.info("管理员账号不存在======" + superUsr.getSuperUsrId() + "======");
			return Result.error(superUsr).message("账号不存在");
		}
		if (!superUsrInDB.getSuperUsrPwd().equals(superUsr.getSuperUsrPwd())) {
			log.info("管理员密码错误======" + superUsr.getSuperUsrId() + "======");
			return Result.error(superUsr).message("密码错误");
		}
		log.debug("管理员登录成功======" + superUsr.getSuperUsrId() + "======");
		return Result.ok(superUsrInDB);
	}

	/**
	 * 管理员信息修改
	 *
	 * @param superUsrOper 请求者信息和请求目标信息
	 * @return Result 如果不是超级管理员，那么返回空列表
	 */
	@ApiOperation(value = "管理员信息修改", notes = "管理员信息修改，只有超级管理员才能修改")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "requestMaker", value = "请求者信息", required = true, dataType = "SuperUsr对象"),
			@ApiImplicitParam(name = "requestTarget", value = "请求目标信息", required = true, dataType = "SuperUsr对象")
	})
	@PatchMapping("/update-super-usr")
	public Result updateSuperUsr(SuperUsrOper superUsrOper) {
		SuperUsr requestTarget = superUsrOper.getRequestTarget();
		Result result = isRoot(superUsrOper.getRequestMaker());
		if (result != null)
			return result;
		requestTarget.setSuperUsrName(superUsrService.getById(requestTarget.getSuperUsrId()).getSuperUsrName());
		superUsrService.updateById(requestTarget);
		log.debug("管理员信息修改成功======" + requestTarget + "======");
		return Result.ok();
	}

	/**
	 * 管理员信息查询
	 *
	 * @param superUsrOper 请求者信息和请求目标信息
	 * @return Result 如果不是超级管理员，那么返回空列表
	 */
	@ApiOperation(value = "管理员信息查询", notes = "管理员信息查询，只有超级管理员才能查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "superUsr", value = "管理员信息", required = true, dataType = "SuperUsr对象")
	})
	@GetMapping("/get-super-usr-list")
	public Result<List<SuperUsr>> getSuperUsrList(SuperUsrOper superUsrOper) {
		SuperUsr requestTarget = superUsrOper.getRequestTarget();
		Result<List<SuperUsr>> result = isRoot(superUsrOper.getRequestMaker(), new ArrayList<>());
		if (result != null)
			return result;
		List<SuperUsr> superUsrList = superUsrService.list(new QueryWrapper<SuperUsr>().like("super_usr_ID", requestTarget.getSuperUsrId()));
		log.debug("管理员信息查询成功======" + superUsrList + "======");
		return Result.ok(superUsrList);
	}

	/**
	 * 管理员信息删除
	 *
	 * @param superUsrOper 请求者信息和请求目标信息
	 * @return Result 如果不是超级管理员，那么返回空列表
	 */
	@ApiOperation(value = "管理员信息删除", notes = "管理员信息删除，只有超级管理员才能删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "requestMaker", value = "请求者信息", required = true, dataType = "SuperUsr对象"),
			@ApiImplicitParam(name = "requestTarget", value = "请求目标信息", required = true, dataType = "SuperUsr对象")
	})
	@DeleteMapping("/delete-super-usr")
	public Result deleteSuperUsr(SuperUsrOper superUsrOper) {
		SuperUsr requestTarget = superUsrOper.getRequestTarget();
		Result result = isRoot(superUsrOper.getRequestMaker());
		if (result != null)
			return result;
		superUsrService.removeById(requestTarget.getSuperUsrId());
		log.debug("管理员信息删除成功======" + requestTarget + "======");
		return Result.ok();
	}

	/**
	 * 管理员信息添加
	 *
	 * @param superUsrOper 请求者信息和请求目标信息
	 * @return Result 如果不是超级管理员，那么返回空列表
	 */
	@ApiOperation(value = "管理员信息添加", notes = "管理员信息添加，只有超级管理员才能添加")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "requestMaker", value = "请求者信息", required = true, dataType = "SuperUsr对象"),
			@ApiImplicitParam(name = "requestTarget", value = "请求目标信息", required = true, dataType = "SuperUsr对象")
	})
	@PostMapping("/add-super-usr")
	public Result addSuperUsr(SuperUsrOper superUsrOper) {
		SuperUsr requestTarget = superUsrOper.getRequestTarget();
		Result result = isRoot(superUsrOper.getRequestMaker());
		if (result != null)
			return result;
		superUsrService.save(requestTarget);
		log.debug("管理员信息添加成功======" + requestTarget + "======");
		return Result.ok();
	}
}

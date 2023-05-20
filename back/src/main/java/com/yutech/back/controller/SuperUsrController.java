package com.yutech.back.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yutech.back.common.utils.JwtUtil;
import com.yutech.back.common.utils.Result;
import com.yutech.back.entity.dto.LoginDTO;
import com.yutech.back.entity.dto.SuperUsrOperationDTO;
import com.yutech.back.entity.po.ServiceProvider;
import com.yutech.back.entity.po.SuperUsr;
import com.yutech.back.entity.po.Usr;
import com.yutech.back.service.persistence.ServiceProviderService;
import com.yutech.back.service.persistence.SuperUsrService;
import com.yutech.back.service.persistence.UsrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 管理员系统，也会被拦截器拦截
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

	@Value("${super.usr.id}")
	private String superUsrId;

	@Autowired
	private SuperUsrService superUsrService;

	@Autowired
	private ServiceProviderService serviceProviderService;

	@Autowired
	private UsrService usrService;


//	/**
//	 * 检测输入是否为超级管理员
//	 *
//	 * @param superUsr 管理员信息
//	 * @return null表示是超级管理员
//	 */
//	private Result<Object> isRoot(SuperUsr superUsr) {
//		if (!superUsr.getSuperUsrId().equals(superUsrId)) {
//			log.info("管理员权限不足===" + superUsr.getSuperUsrId());
//			return Result.error().message("权限不足");
//		}
//		SuperUsr superUsrInDB = superUsrService.getOne(new QueryWrapper<SuperUsr>().eq("super_usr_ID", superUsr.getSuperUsrId()));
//		if (!superUsrInDB.getSuperUsrPwd().equals(superUsr.getSuperUsrPwd())) {
//			log.info("管理员密码错误===" + superUsr.getSuperUsrId());
//			return Result.error().message("密码错误");
//		}
//		return null;
//	}

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
			log.info("管理员权限不足===" + superUsr.getSuperUsrId());
			return Result.error(data).message("权限不足");
		}
		SuperUsr superUsrInDB = superUsrService.getOne(new QueryWrapper<SuperUsr>().eq("super_usr_ID", superUsr.getSuperUsrId()));
		if (!superUsrInDB.getSuperUsrPwd().equals(superUsr.getSuperUsrPwd())) {
			log.info("管理员密码错误===" + superUsr.getSuperUsrId());
			return Result.error(data).message("密码错误");
		}
		return null;
	}

	/**
	 * 管理员登录
	 *
	 * @param account 账号
	 * @param pwd     密码
	 * @return
	 */
	@ApiOperation(value = "管理员登录", notes = "管理员登录，验证账号密码并发放token")
	@GetMapping("/login")
	public Result<String> login(String account, String pwd) {
		LoginDTO loginDTO = new LoginDTO(account, pwd);
		log.debug("管理员登录==={}", loginDTO);
		SuperUsr superUsrInDB = superUsrService.getById(loginDTO.getAccount());
		if (superUsrInDB == null) {
			log.info("管理员账号不存在==={}", loginDTO);
			return Result.error("").message("账号不存在");
		}
		if (!superUsrInDB.getSuperUsrPwd().equals(loginDTO.getPwd())) {
			log.info("管理员密码错误==={}", loginDTO);
			return Result.error("").message("密码错误");
		}
		log.debug("管理员登录成功==={}", superUsrInDB);
		return Result.ok(JwtUtil.sign(superUsrInDB.getSuperUsrId(), superUsrInDB.getSuperUsrPwd())).message("登录成功");
	}

	/**
	 * 管理员信息获取
	 *
	 * @param token
	 * @return
	 */
	@ApiOperation(value = "管理员信息获取", notes = "管理员信息获取，验证token并返回管理员信息")
	@GetMapping("/info")
	public Result<SuperUsr> getInfo(String token) {
		String usrId = JwtUtil.getId(token);
		SuperUsr superUsrInDB = superUsrService.getById(usrId);
		if (superUsrInDB == null) {
			log.info("管理员账号不存在==={}", usrId);
			return Result.error(new SuperUsr()).message("账号不存在");
		}
		log.debug("管理员登录成功==={}", superUsrInDB);
		return Result.ok(superUsrInDB).message("登录成功");
	}

	@GetMapping("/logout")
	@ApiOperation(value = "管理员登出", notes = "管理员登出，验证token并登出")
	public Result<Object> logout(String token) {
		return Result.ok();
	}

	/**
	 * 管理员信息修改
	 *
	 * @param superUsrOperationDTO 请求者信息和请求目标信息
	 * @return Result 如果不是超级管理员，那么返回空列表
	 */
	@ApiOperation(value = "修改管理员", notes = "管理员信息修改，只有超级管理员才能修改")
	@PatchMapping("/update-super-usr")
	public Result<Object> updateSuperUsr(@RequestBody SuperUsrOperationDTO superUsrOperationDTO) {
		SuperUsr requestTarget = superUsrOperationDTO.getRequestTarget();
		Result<Object> result = isRoot(superUsrOperationDTO.getRequestMaker(), null);
		if (result != null) return result;
		requestTarget.setSuperUsrName(superUsrService.getById(requestTarget.getSuperUsrId()).getSuperUsrName());
		superUsrService.updateById(requestTarget);
		log.debug("管理员信息修改成功===" + requestTarget);
		return Result.ok().message("修改成功");
	}

	/**
	 * 管理员信息查询
	 *
	 * @return Result 如果不是超级管理员，那么返回空列表
	 */
	@ApiOperation(value = "获取管理员列表", notes = "管理员信息查询，只有超级管理员才能查询")
	@GetMapping("/get-super-usr-list")
	public Result<List<SuperUsr>> getSuperUsrList() {
		List<SuperUsr> superUsrList = superUsrService.list();
		log.debug("管理员信息查询成功==={}", superUsrList);
		return Result.ok(superUsrList).message("查询成功");
	}

	/**
	 * 管理员信息删除
	 *
	 * @param superUsrOperationDTO 请求者信息和请求目标信息
	 * @return Result 如果不是超级管理员，那么返回空列表
	 */
	@ApiOperation(value = "删除管理员", notes = "管理员信息删除，只有超级管理员才能删除")
	@DeleteMapping("/delete-super-usr")
	public Result<Object> deleteSuperUsr(SuperUsrOperationDTO superUsrOperationDTO) {
		SuperUsr requestTarget = superUsrOperationDTO.getRequestTarget();
		Result<Object> result = isRoot(superUsrOperationDTO.getRequestMaker(), null);
		if (result != null) return result;
		superUsrService.removeById(requestTarget.getSuperUsrId());
		log.debug("管理员信息删除成功==={}", requestTarget);
		return Result.ok().message("删除成功");
	}

	/**
	 * 管理员信息添加
	 *
	 * @param superUsrOperationDTO 请求者信息和请求目标信息
	 * @return Result 如果不是超级管理员，那么返回空列表
	 */
	@ApiOperation(value = "添加管理员", notes = "管理员信息添加，只有超级管理员才能添加")
	@PostMapping("/add-super-usr")
	public Result<Object> addSuperUsr(@RequestBody SuperUsrOperationDTO superUsrOperationDTO) {
		SuperUsr requestTarget = superUsrOperationDTO.getRequestTarget();
		Result<Object> result = isRoot(superUsrOperationDTO.getRequestMaker(), null);
		if (result != null) return result;
		if (superUsrService.getById(requestTarget.getSuperUsrId()) != null)
			return Result.error().message("该管理员已存在，添加失败");
		requestTarget.setSuperUsrId("00005");
		superUsrService.mySave(requestTarget);
		log.debug("管理员信息添加成功==={}", requestTarget);
		return Result.ok().message("添加成功");
	}

	/**
	 * 服务商信息添加
	 *
	 * @param serviceProvider
	 * @return
	 */
	@PatchMapping("/update-service-provider")
	@ApiOperation(value = "修改服务商", notes = "修改服务商，ID不能被修改")
	public Result<Object> updateServiceProvider(ServiceProvider serviceProvider) {
		if (serviceProviderService.getById(serviceProvider.getServiceProviderId()) == null) {
			log.info("该服务商不存在，修改失败==={}", serviceProvider);
			return Result.error().message("该服务商不存在，修改失败");
		}
		serviceProviderService.updateById(serviceProvider);
		log.debug("修改服务商成功==={}", serviceProvider);
		return Result.ok().message("修改成功");
	}

	/**
	 * 服务商信息查询
	 *
	 * @return list
	 */
	@GetMapping("/get-service-provider-list")
	@ApiOperation(value = "获取服务商列表", notes = "获取服务商列表")
	public Result<List<ServiceProvider>> getServiceProviderList() {
		List<ServiceProvider> serviceProviderList = serviceProviderService.list();
		log.debug("获取服务商列表成功==={}", serviceProviderList);
		return Result.ok(serviceProviderList).message("查询成功");
	}

	/**
	 * 服务商信息删除
	 *
	 * @param serviceProviderId
	 * @return
	 */
	@DeleteMapping("/delete-service-provider")
	@ApiOperation(value = "删除服务商", notes = "删除服务商")
	@ApiParam(name = "serviceProviderId", value = "被删除服务商的id", required = true)
	public Result<Object> deleteServiceProvider(@RequestBody int serviceProviderId) {
		if (serviceProviderService.getById(serviceProviderId) == null) {
			log.info("该服务商不存在，删除失败==={}", serviceProviderId);
			return Result.error().message("该服务商不存在，删除失败");
		}
		serviceProviderService.removeById(serviceProviderId);
		log.debug("删除服务商成功==={}", serviceProviderId);
		return Result.ok().message("删除成功");
	}

	/**
	 * 服务商信息添加
	 *
	 * @param serviceProvider
	 * @return
	 */
	@PostMapping("/add-service-provider")
	@ApiOperation(value = "添加服务商", notes = "添加服务商")
	public Result<Object> addServiceProvider(ServiceProvider serviceProvider) {
		if (serviceProviderService.getById(serviceProvider.getServiceProviderId()) != null) {
			log.info("该服务商已存在，不能重复添加==={}", serviceProvider);
			return Result.error().message("该服务商已存在，添加失败");
		}
		serviceProviderService.save(serviceProvider);
		log.debug("添加服务商成功==={}", serviceProvider);
		return Result.ok().message("添加成功");
	}

	/**
	 * 用户直接修改
	 *
	 * @param usr
	 * @return
	 */
	@PatchMapping("/update-usr")
	@ApiOperation(value = "修改用户", notes = "绕过策略直接修改用户，注意ID不能被修改")
	@ApiParam(name = "usr", value = "被修改的用户。注意如果某属性为空，则将数据库中属性也置空！因此需要传入完整的Usr对象", required = true)
	public Result<Object> updateUsr(Usr usr) {
		if (usrService.getById(usr.getUsrId()) == null) {
			log.info("该用户不存在==={}", usr);
			return Result.error().message("该用户不存在，修改失败");
		}
		usrService.updateById(usr);
		log.debug("修改用户成功===", usr);
		return Result.ok().message("修改成功");
	}

	/**
	 * 获取用户列表
	 *
	 * @return
	 */
	@GetMapping("/get-usr-list")
	@ApiOperation(value = "获取用户列表", notes = "获取用户列表")
	public Result<List<Usr>> getUsrList() {
		List<Usr> usrList = usrService.list();
		log.debug("获取用户列表成功===", usrList);
		return Result.ok(usrList).message("查询成功");
	}

	/**
	 * 直接删除用户
	 *
	 * @param usrId
	 * @return
	 */
	@DeleteMapping("/delete-usr")
	@ApiOperation(value = "删除用户", notes = "删除用户")
	public Result<Object> deleteUsr(String usrId) {
		if (usrService.getById(usrId) == null) return Result.error().message("该用户不存在，删除失败");
		usrService.removeById(usrId);
		log.debug("删除用户成功===", usrId);
		return Result.ok().message("删除成功");
	}

	/**
	 * 直接添加用户
	 *
	 * @param usr
	 * @return
	 */
	@PostMapping("/add-usr")
	@ApiOperation(value = "添加用户", notes = "绕过策略直接添加用户")
	public Result<Object> addUsr(Usr usr) {
		if (usrService.getById(usr.getUsrId()) != null) {
			log.info("该用户已存在，不能重复添加==={}", usr);
			return Result.error().message("该用户已存在，添加失败");
		}
		usrService.save(usr);
		log.debug("添加用户成功==={}", usr);
		return Result.ok().message("添加成功");
	}
}


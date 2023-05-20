package com.yutech.back.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yutech.back.common.utils.JwtUtil;
import com.yutech.back.common.utils.Result;
import com.yutech.back.entity.dto.LoginDTO;
import com.yutech.back.entity.po.Aircraft;
import com.yutech.back.entity.po.ServiceProvider;
import com.yutech.back.service.persistence.AircraftService;
import com.yutech.back.service.persistence.ServiceProviderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-03
 */
@RestController
@RequestMapping("/service-provider")
@Slf4j
@Api(tags = "服务商系统")
@CrossOrigin
public class ServiceProviderController {

	@Autowired
	private ServiceProviderService serviceProviderService;

	@Autowired
	private AircraftService aircraftService;

	@ApiOperation(value = "服务商登录", notes = "服务商登录，需要传入ID和密码")
	@GetMapping("/login")
	public Result<String> login(String account, String pwd) {
		LoginDTO loginDTO = new LoginDTO(account, pwd);
		ServiceProvider serviceProviderInDB = serviceProviderService.getById(loginDTO.getAccount());
		if (serviceProviderInDB == null) {
			log.info("账号不存在==={}", loginDTO);
			return Result.error("").message("账号不存在");
		}
		if (!serviceProviderInDB.getServiceProviderPwd().equals(loginDTO.getPwd())) {
			log.info("密码错误==={}", loginDTO);
			return Result.error("").message("密码错误");
		}
		log.debug("登录成功==={}", loginDTO);
		return Result.ok(
						JwtUtil.sign(String.valueOf(serviceProviderInDB.getServiceProviderId()), serviceProviderInDB.getServiceProviderPwd()))
				.message("登录成功");
	}

	@ApiOperation(value = "服务商信息获取", notes = "服务商信息获取，需要传入token")
	@GetMapping("/info")
	public Result<ServiceProvider> getInfo(String token) {
		Integer serviceProviderId = Integer.parseInt(JwtUtil.getId(token));
		ServiceProvider serviceProviderInDB = serviceProviderService.getOne(new QueryWrapper<ServiceProvider>().eq("service_provider_ID", serviceProviderId));
		if (serviceProviderInDB == null) {
			log.info("服务商信息获取失败，服务商不存在，服务商为======{}", serviceProviderId);
			return Result.error(new ServiceProvider()).message("该用户不存在");
		}
		log.debug("用户信息获取成功，用户为======{}", serviceProviderInDB);
		return Result.ok(serviceProviderInDB).message("用户信息获取成功");
	}

	@ApiOperation(value = "服务商信息更新", notes = "服务商信息更新，需要传入token")
	@GetMapping("/update")
	public Result<Object> update(ServiceProvider serviceProvider) {
		if (serviceProviderService.getById(serviceProvider.getServiceProviderId()) == null) {
			log.info("服务商不存在===" + serviceProvider);
			return Result.error().message("服务商不存在");
		}
		serviceProviderService.updateById(serviceProvider);
		log.debug("服务商更新成功===" + serviceProvider);
		return Result.ok().message("更新成功");
	}

	@PostMapping("/add-aircraft")
	@ApiOperation(value = "添加飞机", notes = "添加飞机")
	public Result<Object> addAircraft(Aircraft aircraft) {
		aircraft.setAircraftId("0");
		aircraft.setAircraftStatus("可用");
		log.debug("添加飞机==={}", aircraft);
		aircraftService.save(aircraft);
		return Result.ok().message("添加成功");
	}

	@PostMapping("/add-aircraft-list")
	@ApiOperation(value = "批量飞机列表", notes = "批量添加飞机")
	public Result<Object> addAircraftList(@RequestBody List<Aircraft> aircrafts) {
		log.debug("批量添加飞机==={}", aircrafts);
		aircraftService.saveBatch(aircrafts);
		return Result.ok().message("添加成功");
	}
}


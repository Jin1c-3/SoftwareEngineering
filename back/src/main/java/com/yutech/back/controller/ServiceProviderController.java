package com.yutech.back.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yutech.back.common.exception.GlobalException;
import com.yutech.back.common.utils.JwtUtil;
import com.yutech.back.common.utils.Result;
import com.yutech.back.entity.dto.LoginDTO;
import com.yutech.back.entity.po.Aircraft;
import com.yutech.back.entity.po.FlightInfoDetail;
import com.yutech.back.entity.po.FlightTicket;
import com.yutech.back.entity.po.ServiceProvider;
import com.yutech.back.entity.vo.AircraftBenefitVO;
import com.yutech.back.service.persistence.AircraftService;
import com.yutech.back.service.persistence.FlightInfoDetailService;
import com.yutech.back.service.persistence.FlightTicketService;
import com.yutech.back.service.persistence.ServiceProviderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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
@Api(tags = "服务商接口")
@CrossOrigin
public class ServiceProviderController {

	@Autowired
	private ServiceProviderService serviceProviderService;

	@Autowired
	private AircraftService aircraftService;

	@Autowired
	private FlightInfoDetailService flightInfoDetailService;

	@Autowired
	private FlightTicketService flightTicketService;

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
		try {
			if (serviceProviderService.getById(serviceProvider.getServiceProviderId()) == null) {
				log.info("服务商不存在===" + serviceProvider);
				return Result.error().message("服务商不存在");
			}
			serviceProviderService.updateById(serviceProvider);
		} catch (Exception e) {
			throw new GlobalException("服务商查询异常", e);
		}
		log.debug("服务商更新成功===" + serviceProvider);
		return Result.ok().message("更新成功");
	}

	@PostMapping("/add-aircraft")
	@ApiOperation(value = "添加飞机", notes = "添加飞机")
	public Result<Object> addAircraft(@RequestBody Aircraft aircraft) {
		aircraft.setAircraftId("0");
		aircraft.setAircraftStatus("可用");
		log.debug("添加飞机==={}", aircraft);
		try {
			aircraftService.save(aircraft);
		} catch (Exception e) {
			log.info("添加飞机失败==={}", aircraft);
			return Result.error().message("添加失败");
		}
		return Result.ok().message("添加成功");
	}

	@PostMapping("/add-aircraft-list")
	@ApiOperation(value = "批量飞机列表", notes = "批量添加飞机")
	public Result<Object> addAircraftList(@RequestBody List<Aircraft> aircrafts) {
		log.debug("批量添加飞机==={}", aircrafts);
		try {
			aircraftService.saveBatch(aircrafts);
		} catch (Exception e) {
			log.info("批量添加飞机失败==={}", aircrafts);
			return Result.error().message("添加失败");
		}
		return Result.ok().message("添加成功");
	}

	@DeleteMapping("/delete-aircraft")
	@ApiOperation(value = "删除飞机", notes = "删除飞机")
	public Result<Object> deleteAircraft(@RequestBody String aircraftId) {
		log.debug("删除飞机==={}", aircraftId);
		try {
			aircraftService.removeById(aircraftId);
		} catch (Exception e) {
			log.info("删除飞机失败==={}", aircraftId);
			return Result.error().message("删除失败");
		}
		return Result.ok().message("删除成功");
	}

	@GetMapping("/get-aircraft-list")
	@ApiOperation(value = "获取飞机列表", notes = "获取飞机列表")
	public Result<List<Aircraft>> getAircraftList() {
		List<Aircraft> aircraftList = null;
		try {
			aircraftList = aircraftService.list();
		} catch (Exception e) {
			log.info("通过服务商ID获取飞机列表失败");
			return Result.error(aircraftList).message("获取失败");
		}
		log.trace("通过服务商ID获取飞机列表==={}", aircraftList);
		return Result.ok(aircraftList).message("获取成功");
	}

	@GetMapping("/get-aircraft-list-by-id")
	@ApiOperation(value = "通过服务商ID获取飞机列表", notes = "通过服务商ID获取飞机列表")
	public Result<List<Aircraft>> getAircraftListById(String serviceProviderId) {
		List<Aircraft> aircraftList = null;
		try {
			aircraftList = aircraftService.list(new QueryWrapper<Aircraft>().eq("service_provider_ID", serviceProviderId));
		} catch (Exception e) {
			log.info("通过服务商ID获取飞机列表失败==={}", serviceProviderId);
			return Result.error(aircraftList).message("获取失败");
		}
		log.trace("通过服务商ID获取飞机列表==={}", aircraftList);
		return Result.ok(aircraftList).message("获取成功");
	}

	@PatchMapping("/update-aircraft")
	@ApiOperation(value = "更新飞机", notes = "更新飞机")
	public Result<Object> updateAircraft(@RequestBody Aircraft aircraft) {
		log.debug("更新飞机==={}", aircraft);
		if (aircraftService.getById(aircraft.getAircraftId()) == null) {
			log.info("飞机不存在===" + aircraft);
			return Result.error().message("飞机不存在");
		}
		try {
			aircraftService.updateById(aircraft);
		} catch (Exception e) {
			log.info("更新失败===" + aircraft);
			return Result.error().message("更新失败");
		}
		return Result.ok().message("更新成功");
	}

	@GetMapping("/query-benefit")
	@ApiOperation(value = "查询收益", notes = "查询收益")
	public Result<List<AircraftBenefitVO>> queryBenefit(@RequestParam Integer serviceProviderId) {
		BigDecimal pushMoney = serviceProviderService.getOne(new QueryWrapper<ServiceProvider>().eq("service_provider_ID", serviceProviderId)).getPushMoney();
		List<String> aircraftIdList;
		List<AircraftBenefitVO> aircraftBenefitVOList = new ArrayList<>();

		aircraftService.list(new QueryWrapper<Aircraft>()
						.eq("service_provider_ID", serviceProviderId))
				.forEach(aircraft -> {
					AircraftBenefitVO aircraftBenefitVO = new AircraftBenefitVO();
					aircraftBenefitVO.setAircraftId(aircraft.getAircraftId());
					flightInfoDetailService.list(new QueryWrapper<FlightInfoDetail>()
									.eq("aircraft_ID", aircraft.getAircraftId()))
							.forEach(flightInfoDetail -> {
								List<FlightTicket> flightTicketList = flightTicketService.list(new QueryWrapper<FlightTicket>()
										.eq("flight_ID", flightInfoDetail.getFlightId()));
								aircraftBenefitVO.setTotalBenefit(flightTicketList.stream().map(FlightTicket::getFlightPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
								aircraftBenefitVO.setFlightTicketNum(flightTicketList.size());
							});
					aircraftBenefitVO.setTrueBenefit(aircraftBenefitVO.getTotalBenefit().multiply(BigDecimal.ONE.subtract(pushMoney)));
					aircraftBenefitVOList.add(aircraftBenefitVO);
				});
		return Result.ok(aircraftBenefitVOList).message("查询成功");
	}
}


package com.yutech.back.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yutech.back.common.utils.Result;
import com.yutech.back.entity.po.Passenger;
import com.yutech.back.service.persistence.PassengerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 乘客控制器，乘客是多重ID，所以需要MPP
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-03
 */
@RestController
@RequestMapping("/passenger")
@Api(tags = "乘客接口")
@Slf4j
@CrossOrigin
public class PassengerController {

	@Autowired
	private PassengerService passengerService;

	/**
	 * 根据用户id查询乘客信息
	 *
	 * @param usrId 用户id
	 * @return 乘客信息列表
	 */
	@GetMapping("/findPsgByUsrId")
	@ApiOperation(value = "根据用户id查询乘客信息", notes = "用户id和PassengerID是互通的")
	@ApiImplicitParam(name = "usrId", value = "用户id", required = true, dataTypeClass = String.class)
	public Result<List<Passenger>> findPsgByUsrId(String usrId) {
		log.debug("根据usrId查询乘客信息=======" + usrId);
		return Result.ok(passengerService.list(new QueryWrapper<Passenger>().eq("usr_id", usrId))).message("查询成功");
	}

	@PostMapping("/addPassenger")
	@ApiOperation(value = "添加乘客信息", notes = "添加一条乘客信息，前端弄一个页面添加就行")
	public Result<Object> addPassenger(@RequestBody Passenger passenger) {
		log.debug("添加乘客信息===" + passenger);
		Passenger passengerInDB = passengerService.getOne(new QueryWrapper<Passenger>()
				.eq("passenger_id", passenger.getPassengerId())
				.eq("usr_id", passenger.getUsrId()));
		if (passengerInDB != null) {
			log.info("添加乘客信息失败，该乘客已存在===" + passenger);
			return Result.error().message("添加失败，该乘客已存在");
		}
		try {
			passengerService.save(passenger);
			log.debug("添加乘客信息成功===" + passenger);
			return Result.ok().message("添加成功");
		} catch (Exception e) {
			log.error("添加乘客信息失败==={}==={}", passenger, e.getMessage());
			return Result.error().message("添加失败");
		}
	}

	@DeleteMapping("/deletePassenger")
	@ApiOperation(value = "删除乘客信息", notes = "删除乘客信息")
	public Result<Object> deletePassenger(@RequestBody Passenger passenger) {
		log.debug("删除乘客信息===" + passenger);
		Passenger passengerInDB = passengerService.getOne(new QueryWrapper<Passenger>()
				.eq("passenger_id", passenger.getPassengerId())
				.eq("usr_id", passenger.getUsrId()));
		if (passengerInDB == null) {
			log.info("添加乘客信息失败，该乘客不存在===" + passenger);
			return Result.error().message("添加失败，该乘客不存在");
		}
		try {
			passengerService.remove(new QueryWrapper<Passenger>()
					.eq("passenger_id", passenger.getPassengerId())
					.eq("usr_id", passenger.getUsrId()));
			log.debug("删除乘客信息成功===" + passenger);
			return Result.ok().message("删除成功");
		} catch (Exception e) {
			log.error("删除乘客信息失败==={}==={}", passenger, e.getMessage());
			return Result.error().message("删除失败");
		}
	}
}


package com.yutech.back.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yutech.back.common.exception.GlobalException;
import com.yutech.back.common.utils.OtherUtil;
import com.yutech.back.common.utils.Result;
import com.yutech.back.entity.dto.AircraftSeatDTO;
import com.yutech.back.entity.dto.TicketQueryDTO;
import com.yutech.back.entity.po.AircraftSeat;
import com.yutech.back.entity.po.FlightInfo;
import com.yutech.back.entity.po.FlightInfoDetail;
import com.yutech.back.service.persistence.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-05-26
 */
@RestController
@RequestMapping("/aircraft")
@Slf4j
@CrossOrigin
@Api(tags = "飞机管理")
@Validated
public class AircraftController {

	@Autowired
	private AircraftAvailableSeats1Service aircraftAvailableSeats1Service;

	@Autowired
	private AircraftAvailableSeats12Service aircraftAvailableSeats12Service;

	@Autowired
	private AircraftAvailableSeats123Service aircraftAvailableSeats123Service;

	@Autowired
	private AircraftAvailableSeats2Service aircraftAvailableSeats2Service;

	@Autowired
	private AircraftAvailableSeats23Service aircraftAvailableSeats23Service;

	@Autowired
	private AircraftAvailableSeats3Service aircraftAvailableSeats3Service;

	@Autowired
	private AircraftSeatService aircraftSeatService;

	@Autowired
	private FlightInfoDetailService flightInfoDetailService;

	@Autowired
	private FlightInfoService flightInfoService;

	/**
	 * 根据航班ID查询航班的站点信息
	 *
	 * @param flightId
	 * @return
	 */
	@GetMapping("/query-flight-by-id")
	@ApiOperation("根据航班ID查询航班的站点信息")
	public Result<List<FlightInfoDetail>> queryFlightById(@NotBlank(message = "航班号不能为空")
	                                                      @RequestParam String flightId) {
		log.debug("查询航班信息前端信息==={}", flightId);
		List<FlightInfoDetail> flightInfoList = null;
		try {
			flightInfoList = flightInfoDetailService.list(new QueryWrapper<FlightInfoDetail>().eq("flight_id", flightId));
		} catch (Exception e) {
			return Result.error(flightInfoList).message("查询航班信息失败");
		}
		log.trace("查询航班信息结果==={}", flightInfoList);
		return Result.ok(flightInfoList).message(flightInfoList == null ? "该航班不存在" : "查询航班信息成功");
	}

	@GetMapping("/query-flight-by-id-date")
	@ApiOperation("根据航班ID和日期查询航班的站点信息")
	public Result<List<FlightInfoDetail>> queryFlightByIdDate(@NotBlank(message = "航班号不能为空")
	                                                          @RequestParam String flightId,
	                                                          @NotBlank(message = "日期不能为空")
	                                                          @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "日期格式不正确")
	                                                          @RequestParam String date) {
		log.debug("查询航班信息前端信息==={}==={}", flightId, date);
		int weekDay = OtherUtil.getWeek(date);

		boolean weekDayFlag = false;
		try {
			FlightInfo flightInfo = flightInfoService.getOne(new QueryWrapper<FlightInfo>().eq("flight_id", flightId));
			if (flightInfo.getFlightSchedule().contains(weekDay + "")) weekDayFlag = true;
		} catch (Exception e) {
			throw new GlobalException("查询航班信息失败", e);
		}

		List<FlightInfoDetail> flightInfoList = null;
		if (weekDayFlag) {
			try {
				flightInfoList = flightInfoDetailService.list(new QueryWrapper<FlightInfoDetail>().eq("flight_id", flightId).eq("week_day", weekDay));
			} catch (Exception e) {
				return Result.error(flightInfoList).message("查询航班信息失败");
			}
			log.trace("查询航班信息结果==={}", flightInfoList);
			return Result.ok(flightInfoList).message(flightInfoList == null ? "该航班不存在" : "查询航班信息成功");
		}
		return Result.error(flightInfoList).message("该航班今日不飞行");

	}

	/**
	 * 查询航班
	 *
	 * @param ticketQueryDTO
	 * @return
	 */
	@PostMapping("/query-flight")
	@ApiOperation(value = "查询航班", notes = "只负责查询航班，不管有没有余票")
	public Result<List<FlightInfoDetail>> queryFlight(@Validated @RequestBody TicketQueryDTO ticketQueryDTO) {
		log.debug("查询航班信息前端信息==={}", ticketQueryDTO);
		List<String> startFlights = null;
		List<String> endFlights = null;
		List<String> trueFlights = null;
		List<FlightInfoDetail> flightInfoList = null;
		try {
			flightInfoDetailService.list(new QueryWrapper<FlightInfoDetail>()
							.eq("flight_start_city", ticketQueryDTO.getStartCity()))
					.stream().forEach(flightInfoDetail -> startFlights.add(flightInfoDetail.getFlightId()));
			flightInfoDetailService.list(new QueryWrapper<FlightInfoDetail>()
							.eq("flight_end_city", ticketQueryDTO.getEndCity()))
					.stream().forEach(flightInfoDetail -> endFlights.add(flightInfoDetail.getFlightId()));
		} catch (GlobalException e) {
			throw new GlobalException("查询航班信息失败", e);
		}
		try {
			startFlights.stream().forEach(startFlight -> {
				endFlights.stream().forEach(endFlight -> {
					if (startFlight.equals(endFlight)) trueFlights.add(startFlight);
				});
			});
			trueFlights.stream().forEach(trueFlight -> {
				try {
					flightInfoList.add(flightInfoDetailService.getOne(new QueryWrapper<FlightInfoDetail>().eq("flight_id", trueFlight)));
				} catch (GlobalException e) {
					throw new GlobalException("查询航班信息失败", e);
				}
			});
		} catch (Exception e) {
			return Result.error(flightInfoList).message("暂无此航班");
		}
		log.trace("查询航班信息结果==={}", flightInfoList);
		return Result.ok(flightInfoList).message("查询航班信息成功");
	}

	/**
	 * 查询航班座位信息
	 * 根据传入对象的空缺值情况自动进行调整
	 *
	 * @param aircraftSeatDTO
	 * @return
	 */
	@GetMapping("/query-aircraft-seat")
	@ApiOperation("查询航班座位信息")
	public Result<List<AircraftSeat>> queryAircraftSeat(@Validated AircraftSeatDTO aircraftSeatDTO) {
		List<AircraftSeat> aircraftSeatList;
		try {
			aircraftSeatList = aircraftSeatService.list(new QueryWrapper<AircraftSeat>()
					.eq("flight_no", aircraftSeatDTO.getFlightNo())
					.eq("station_order", aircraftSeatDTO.getStationOrder())
					.eq("date", aircraftSeatDTO.getDate()));
		} catch (GlobalException e) {
			throw new GlobalException("查询航班座位信息失败", e);
		}

		if (aircraftSeatDTO.getSeatType() != null && aircraftSeatList != null) {
			aircraftSeatList = aircraftSeatList.stream()
					.filter(aircraftSeat -> aircraftSeat.getSeatType().equals(aircraftSeatDTO.getSeatType()))
					.collect(Collectors.toList());
		}
		if (aircraftSeatDTO.getSeatNo() != null && aircraftSeatList != null) {
			aircraftSeatList = aircraftSeatList.stream()
					.filter(aircraftSeat -> aircraftSeat.getSeatNo().equals(aircraftSeatDTO.getSeatNo()))
					.collect(Collectors.toList());
		}
		return Result.ok(aircraftSeatList).message(aircraftSeatList == null ? "暂无此座位" : "查询航班座位信息成功");
	}
}


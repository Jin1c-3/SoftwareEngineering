package com.yutech.back.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yutech.back.common.exception.GlobalException;
import com.yutech.back.common.utils.DateUtil;
import com.yutech.back.common.utils.Result;
import com.yutech.back.entity.bo.AircraftAvailableSeats;
import com.yutech.back.entity.dto.AircraftSeatDTO;
import com.yutech.back.entity.dto.TicketQueryDTO;
import com.yutech.back.entity.po.*;
import com.yutech.back.service.persistence.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
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
@Api(tags = "飞机接口")
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
	private FlightTicketService flightTicketService;

	/**
	 * 根据航班ID查询航班的站点信息
	 *
	 * @param flightId
	 * @return
	 */
	@GetMapping("/query-flight-by-id")
	@ApiOperation("航班ID查询航班的站点信息")
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
	@ApiOperation("航班ID+日期查询航班的站点信息")
	public Result<List<FlightInfoDetail>> queryFlightByIdDate(@NotBlank(message = "航班号不能为空")
	                                                          @RequestParam String flightId,
	                                                          @NotBlank(message = "日期不能为空")
	                                                          @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "日期格式不正确")
	                                                          @RequestParam String date) {
		int weekDay = DateUtil.getWeek(date);
		log.debug("查询航班信息前端信息==={}==={}==={}", flightId, date, weekDay);
		List<FlightInfoDetail> flightInfoDetailList = new ArrayList<>();

		try {
			flightInfoDetailService.list(new QueryWrapper<FlightInfoDetail>()
							.eq("flight_id", flightId))
					.forEach(flightInfoDetail -> {
						if (flightInfoDetail.getFlightSchedule().contains(weekDay + "")) {
							flightInfoDetailList.add(flightInfoDetail);
						}
					});
		} catch (Exception e) {
			throw new GlobalException("查询航班信息失败，星期转换错误", e);
		}
		log.trace("查询航班信息结果==={}", flightInfoDetailList);
		return Result.ok(flightInfoDetailList).message(flightInfoDetailList.isEmpty() ? "该航班不存在" : "查询航班信息成功");

	}

	/**
	 * 查询航班
	 *
	 * @param ticketQueryDTO
	 * @return
	 */
	@PostMapping("/query-flight")
	@ApiOperation(value = "查询满足条件的航班", notes = "只负责查询航班，不管有没有余票")
	public Result<List<List<FlightInfoDetail>>> queryFlight(@Validated @RequestBody TicketQueryDTO ticketQueryDTO) {
		log.debug("查询航班信息前端信息==={}", ticketQueryDTO);
		int weekDay = DateUtil.getWeek(ticketQueryDTO.getLeaveYearMonthDay());
		List<String> startFlights = new ArrayList<String>();
		List<String> endFlights = new ArrayList<String>();
		List<String> trueFlights = new ArrayList<String>();
		List<List<FlightInfoDetail>> flightInfoDetailList = new ArrayList<>();
		try {
			flightInfoDetailService.list(new QueryWrapper<FlightInfoDetail>()
							.eq("flight_start_city", ticketQueryDTO.getStartCityOrStation())
							.and(flightInfoDetailQueryWrapper -> flightInfoDetailQueryWrapper.like("flight_schedule", weekDay + "")))
					.stream().forEach(flightInfoDetail -> {
						startFlights.add(flightInfoDetail.getFlightId() +
								"," + flightInfoDetail.getFlightSchedule());
					});
			flightInfoDetailService.list(new QueryWrapper<FlightInfoDetail>()
							.eq("flight_end_city", ticketQueryDTO.getEndCityOrStation())
							.and(flightInfoDetailQueryWrapper -> flightInfoDetailQueryWrapper.like("flight_schedule", weekDay + "")))
					.stream().forEach(flightInfoDetail -> {
						endFlights.add(flightInfoDetail.getFlightId() +
								"," + flightInfoDetail.getFlightSchedule());
					});
		} catch (Exception e) {
			throw new GlobalException("查询航班信息失败，城市查询出错", e);
		}
		try {
			startFlights.stream().forEach(startFlight -> {
				endFlights.stream().forEach(endFlight -> {
					if (startFlight.equals(endFlight)) trueFlights.add(startFlight);
				});
			});
			trueFlights.stream().forEach(trueFlight -> {
				try {
					List<FlightInfoDetail> flightInfoDetails = new ArrayList<>();
					flightInfoDetails.addAll(flightInfoDetailService.list(new QueryWrapper<FlightInfoDetail>()
							.eq("flight_id", trueFlight.split(",")[0])
							.eq("flight_schedule", trueFlight.split(",")[1])));
					flightInfoDetailList.add(flightInfoDetails);
				} catch (Exception e) {
					throw new GlobalException("查询航班信息失败，航线结果对接出错", e);
				}
			});
		} catch (Exception e) {
			throw new GlobalException("查询航班信息失败", e);
		}
		log.trace("查询航班信息结果==={}", flightInfoDetailList);
		return Result.ok(flightInfoDetailList).message(flightInfoDetailList.isEmpty() ? "暂无此航班" : "查询航班信息成功");
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
		log.debug("查询航班座位信息前端信息==={}", aircraftSeatDTO);
		List<AircraftSeat> aircraftSeatList = new ArrayList<>();
		List<AircraftAvailableSeats> aircraftAvailableSeatsList = new ArrayList<>();

		if (aircraftSeatDTO.getStationOrder() == 1) {
			List<AircraftAvailableSeats1> aircraftAvailableSeats1List = aircraftAvailableSeats1Service
					.list(new QueryWrapper<AircraftAvailableSeats1>()
							.eq("flight_no", aircraftSeatDTO.getFlightNo())
							.eq("date", aircraftSeatDTO.getDate()));
			if (aircraftAvailableSeats1List != null) {
				aircraftAvailableSeatsList.addAll(aircraftAvailableSeats1List);
			}
		}
		if (aircraftSeatDTO.getStationOrder() == 2) {
			List<AircraftAvailableSeats2> aircraftAvailableSeats2List = aircraftAvailableSeats2Service
					.list(new QueryWrapper<AircraftAvailableSeats2>()
							.eq("flight_no", aircraftSeatDTO.getFlightNo())
							.eq("date", aircraftSeatDTO.getDate()));
			if (aircraftAvailableSeats2List != null) {
				aircraftAvailableSeatsList.addAll(aircraftAvailableSeats2List);
			}
		}
		if (aircraftSeatDTO.getStationOrder() == 3) {
			List<AircraftAvailableSeats3> aircraftAvailableSeats3List = aircraftAvailableSeats3Service
					.list(new QueryWrapper<AircraftAvailableSeats3>()
							.eq("flight_no", aircraftSeatDTO.getFlightNo())
							.eq("date", aircraftSeatDTO.getDate()));
			if (aircraftAvailableSeats3List != null) {
				aircraftAvailableSeatsList.addAll(aircraftAvailableSeats3List);
			}
		}
		if (aircraftSeatDTO.getStationOrder() == 12) {
			List<AircraftAvailableSeats12> aircraftAvailableSeats12List = aircraftAvailableSeats12Service
					.list(new QueryWrapper<AircraftAvailableSeats12>()
							.eq("flight_no", aircraftSeatDTO.getFlightNo())
							.eq("date", aircraftSeatDTO.getDate()));
			if (aircraftAvailableSeats12List != null) {
				aircraftAvailableSeatsList.addAll(aircraftAvailableSeats12List);
			}
		}
		if (aircraftSeatDTO.getStationOrder() == 123) {
			List<AircraftAvailableSeats123> aircraftAvailableSeats123List = aircraftAvailableSeats123Service
					.list(new QueryWrapper<AircraftAvailableSeats123>()
							.eq("flight_no", aircraftSeatDTO.getFlightNo())
							.eq("date", aircraftSeatDTO.getDate()));
			if (aircraftAvailableSeats123List != null) {
				aircraftAvailableSeatsList.addAll(aircraftAvailableSeats123List);
			}
		}
		if (aircraftSeatDTO.getStationOrder() == 23) {
			List<AircraftAvailableSeats23> aircraftAvailableSeats23List = aircraftAvailableSeats23Service
					.list(new QueryWrapper<AircraftAvailableSeats23>()
							.eq("flight_no", aircraftSeatDTO.getFlightNo())
							.eq("date", aircraftSeatDTO.getDate()));
			if (aircraftAvailableSeats23List != null) {
				aircraftAvailableSeatsList.addAll(aircraftAvailableSeats23List);
			}
		}

		if (aircraftSeatDTO.getSeatNo() != null && aircraftSeatDTO != null && !aircraftSeatDTO.getSeatNo().equals("")) {
			aircraftAvailableSeatsList = aircraftAvailableSeatsList.stream()
					.filter(aircraftAvailableSeats -> aircraftAvailableSeats.getSeatNo().equals(aircraftSeatDTO.getSeatNo()))
					.collect(Collectors.toList());
		}
		if (aircraftSeatDTO.getSeatType() != null && aircraftSeatDTO != null && !aircraftSeatDTO.getSeatType().equals("")) {
			aircraftAvailableSeatsList = aircraftAvailableSeatsList.stream()
					.filter(aircraftAvailableSeats -> aircraftAvailableSeats.getSeatType().equals(aircraftSeatDTO.getSeatType()))
					.collect(Collectors.toList());
		}
		try {
			aircraftAvailableSeatsList.forEach(aircraftAvailableSeats -> {
				aircraftSeatList.add(new AircraftSeat(aircraftAvailableSeats));
			});
		} catch (Exception e) {
			throw new GlobalException("查询航班座位信息失败", e);
		}
		return Result.ok(aircraftSeatList).message(aircraftSeatList.isEmpty() ? "暂无此座位" : "查询航班座位信息成功");
	}

	@GetMapping("/query-aircraft-ticket")
	@ApiOperation(value = "根据订单号查询机票", notes = "根据订单号查询机票")
	public Result<FlightTicket> queryAircraftTicket(@RequestParam("orderId") String orderId) {
		log.debug("根据订单号查询机票前端信息==={}", orderId);
		FlightTicket flightTicket = new FlightTicket();
		try {
			flightTicket = flightTicketService.getOne(new QueryWrapper<FlightTicket>().eq("order_Id", orderId));
		} catch (Exception e) {
			throw new GlobalException("根据订单号查询机票失败", e);
		}
		return Result.ok(flightTicket).message(flightTicket == null ? "暂无此机票" : "根据订单号查询机票成功");
	}
}


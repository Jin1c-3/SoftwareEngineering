package com.yutech.back.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yutech.back.common.exception.GlobalException;
import com.yutech.back.common.utils.Result;
import com.yutech.back.entity.dto.TicketQueryDTO;
import com.yutech.back.entity.dto.TrainSeatDTO;
import com.yutech.back.entity.po.TrainNumberInfoDetail;
import com.yutech.back.entity.po.TrainTicket;
import com.yutech.back.service.persistence.TrainNumberInfoDetailService;
import com.yutech.back.service.persistence.TrainNumberInfoService;
import com.yutech.back.service.persistence.TrainTicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * 火车票相关操作集合
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-03
 */
@RestController
@RequestMapping("/train")
@Api(tags = "火车接口")
@Slf4j
@CrossOrigin
@Validated
public class TrainController {

	@Autowired
	private TrainNumberInfoDetailService trainNumberInfoDetailService;

	@Autowired
	private TrainNumberInfoService trainNumberInfoService;

	@Autowired
	private TrainTicketService trainTicketService;

//	@GetMapping("/queryTrainTicket")
//	public Result<Object> getQueryTrainTicket(GetTrainDTO getTrainDTO) {
//		log.debug("查询火车票信息前端信息：{}", getTrainDTO);
//		//1.根据出发城市和到达城市查询车次
//		List<String> TrainNumberList = new ArrayList<>();
//		for (TrainNumberInfoDetail i : trainNumberInfoDetailService
//				.list(new QueryWrapper<TrainNumberInfoDetail>()
//						.eq("train_arrive_city", getTrainDTO.getDepartCity()))) {
//			for (TrainNumberInfoDetail j : trainNumberInfoDetailService
//					.list(new QueryWrapper<TrainNumberInfoDetail>()
//							.eq("train_number_ID", i.getTrainNumberId())
//							.eq("train_arrive_city", getTrainDTO.getArrivalCity()))) {
//				TrainNumberList.add(j.getTrainNumberId());
//			}
//		}
//		log.trace("查询火车票信息结果：{}", TrainNumberList);
//
//		//2.根据车次获取火车ID，进而获得火车座位信息
//		return Result.ok();
//	}

	@GetMapping("/query-train-by-id")
	@ApiOperation(value = "车次查询火车路线", notes = "查询火车路线")
	public Result<List<TrainNumberInfoDetail>> queryTrainById(@NotBlank(message = "车次ID不能为空") @Validated
	                                                          @RequestParam String trainNumberId) {
		log.debug("查询火车票信息前端信息==={}", trainNumberId);
		List<TrainNumberInfoDetail> trainNumberInfoDetail = null;
		try {
			trainNumberInfoDetail = trainNumberInfoDetailService.list(new QueryWrapper<TrainNumberInfoDetail>()
					.like("train_number_ID", trainNumberId));
		} catch (Exception e) {
			log.error("查询火车票信息异常==={}", e.getMessage());
			throw new GlobalException("查询火车票信息异常", e);
		}
		log.trace("查询火车票信息结果==={}", trainNumberInfoDetail);
		return Result.ok(trainNumberInfoDetail).message(trainNumberInfoDetail == null ? "暂无此路线" : "查询火车票信息成功");
	}

	private boolean isTransit(TrainNumberInfoDetail start, TrainNumberInfoDetail end) {
		AtomicBoolean isTransit = new AtomicBoolean(false);
		boolean isSameTrain = start.getTrainNumberId().equals(end.getTrainNumberId())
				&& start.getTrainOrder() < end.getTrainOrder();
		if (isSameTrain) {
			return true;
		}
		List<TrainNumberInfoDetail> startTrainNumberInfoDetailList = trainNumberInfoDetailService.list(new QueryWrapper<TrainNumberInfoDetail>()
				.eq("train_number_ID", start.getTrainNumberId()));
		List<TrainNumberInfoDetail> endTrainNumberInfoDetailList = trainNumberInfoDetailService.list(new QueryWrapper<TrainNumberInfoDetail>()
				.eq("train_number_ID", end.getTrainNumberId()));
		startTrainNumberInfoDetailList.stream().filter(startFollowStation -> startFollowStation.getTrainOrder() > start.getTrainOrder())
				.forEach(startFollowStation -> {
					endTrainNumberInfoDetailList.stream().filter(endForwardStation -> endForwardStation.getTrainOrder() < end.getTrainOrder())
							.anyMatch(endForwardStation -> {
								if (startFollowStation.getTrainArriveCity().equals(endForwardStation.getTrainArriveCity())) {
									isTransit.set(true);
									return true;
								}
								return false;
							});
				});
		return isTransit.get();
	}

	@PostMapping("/query-train")
	@ApiOperation(value = "查询满足条件的火车路线", notes = "查询火车路线")
	public Result<List<List<TrainNumberInfoDetail>>> queryTrain(@Validated @RequestBody TicketQueryDTO ticketQueryDTO) {
		log.debug("查询火车路线信息前端信息==={}", ticketQueryDTO);
		List<TrainNumberInfoDetail> startCityOrStation = new ArrayList<>();
		List<TrainNumberInfoDetail> endCityOrStation = new ArrayList<>();
		List<String> trueTrainNumberId = new ArrayList<>();
		List<List<TrainNumberInfoDetail>> trainNumberInfoDetailList = new ArrayList<>();
		try {
			startCityOrStation.addAll(trainNumberInfoDetailService.list(new QueryWrapper<TrainNumberInfoDetail>()
					.eq("train_arrive_city", ticketQueryDTO.getStartCityOrStation())
					.or()
					.eq("train_arrive_station", ticketQueryDTO.getStartCityOrStation())));
			endCityOrStation.addAll(trainNumberInfoDetailService.list(new QueryWrapper<TrainNumberInfoDetail>()
					.eq("train_arrive_city", ticketQueryDTO.getEndCityOrStation())
					.or()
					.eq("train_arrive_station", ticketQueryDTO.getEndCityOrStation())));
		} catch (Exception e) {
			throw new GlobalException("查询火车路线信息异常", e);
		}
		try {
			startCityOrStation.stream().forEach(start -> {
				endCityOrStation.stream().anyMatch(end -> {
					if (isTransit(start, end)) {
						trueTrainNumberId.add(start.getTrainNumberId());
						endCityOrStation.remove(end);
						return true;
					}
					return false;
				});
			});
		} catch (Exception e) {
			throw new GlobalException("合并火车路线信息异常", e);
		}
		try {
			trueTrainNumberId.stream().forEach(trainNumberId -> {
				List<TrainNumberInfoDetail> trainNumberInfoDetails = new ArrayList<>();
				trainNumberInfoDetails.addAll(trainNumberInfoDetailService.list(new QueryWrapper<TrainNumberInfoDetail>()
						.eq("train_number_ID", trainNumberId)));
				trainNumberInfoDetailList.add(trainNumberInfoDetails);
			});
		} catch (Exception e) {
			throw new GlobalException("转换火车路线信息异常", e);
		}
		log.trace("查询火车路线信息结果==={}", trainNumberInfoDetailList);
		return Result.ok(trainNumberInfoDetailList).message(trainNumberInfoDetailList.isEmpty() ? "暂无此路线" : "查询火车路线信息成功");
	}

	@PostMapping("/query-train-seat")
	@ApiOperation(value = "查询火车座位", notes = "查询火车座位")
	public Result<Integer> queryTrainSeat(@RequestBody @Validated TrainSeatDTO trainSeatDTO) {
		log.debug("查询火车座位前端信息==={}", trainSeatDTO);
		int seat = trainNumberInfoDetailService.queryTrainSeat(trainSeatDTO);
		if (seat == -1) throw new GlobalException("火车座位数未改变，查询失败");
		return Result.ok(seat).message(seat == 0 ? "暂无此座位" : "查询火车座位成功");
	}

	@GetMapping("/query-train-ticket")
	@ApiOperation(value = "根据订单号查询火车票", notes = "根据订单号查询火车票")
	public Result<List<TrainTicket>> queryTrainTicket(@NotBlank(message = "订单号不能为空") @Validated
	                                                  @RequestParam String orderId) {
		log.debug("根据订单号查询火车票前端信息==={}", orderId);
		List<TrainTicket> trainTicketList = null;
		try {
			trainTicketList = trainTicketService.list(new QueryWrapper<TrainTicket>().eq("order_id", orderId));
		} catch (Exception e) {
			throw new GlobalException("根据订单号查询火车票异常", e);
		}
		log.trace("根据订单号查询火车票结果==={}", trainTicketList);
		return Result.ok(trainTicketList).message(trainTicketList == null ? "暂无此订单" : "根据订单号查询火车票成功");
	}
}

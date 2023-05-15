package com.yutech.back.controller;

import com.yutech.back.service.persistence.TrainNumberInfoDetailService;
import com.yutech.back.service.persistence.TrainNumberInfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Api(tags = "火车管理")
@Slf4j
@CrossOrigin
public class TrainController {

	@Autowired
	private TrainNumberInfoDetailService trainNumberInfoDetailService;

	@Autowired
	private TrainNumberInfoService trainNumberInfoService;

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
}

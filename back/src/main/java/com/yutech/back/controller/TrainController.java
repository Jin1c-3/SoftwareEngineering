package com.yutech.back.controller;

import com.yutech.back.common.utils.Result;
import com.yutech.back.entity.dto.GetTrainDTO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
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
	@GetMapping("/queryTrainTicket")
	public Result getQueryTrainTicket(GetTrainDTO getTrainDTO) {
		return Result.ok();
	}
}

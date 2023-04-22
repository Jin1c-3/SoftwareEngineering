package com.yutech.back.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "FindTrainDTO对象", description = "查询火车车次表现层对象。始发城市和终点城市不可以相同，否则报错")
public class GetTrainDTO {

	@ApiModelProperty(value = "始发城市", required = true)
	private String DepartCity;

	@ApiModelProperty(value = "终点城市", required = true)
	private String ArrivalCity;

	@ApiModelProperty(value = "用户出发时间，只需要年月日", required = true)
	private LocalDateTime LeaveYearMonthDay;
}

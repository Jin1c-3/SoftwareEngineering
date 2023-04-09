package com.yutech.back.entity.bo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "FindTrainDTO对象", description = "查询火车车次表现层对象")
public class GetTrainDTO {
	@ApiModelProperty(value = "始发城市")
	private String TrainStartStation;
	@ApiModelProperty(value = "终点城市")
	private String TrainEndStation;
	@ApiModelProperty(value = "用户出发时间")
	private LocalDateTime LeaveMonthDay;
}

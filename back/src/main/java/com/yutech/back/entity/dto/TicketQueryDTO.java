package com.yutech.back.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel(value = "FindTrainDTO对象", description = "查询火车车次表现层对象。始发城市和终点城市不可以相同，否则报错")
public class TicketQueryDTO {

	@ApiModelProperty(value = "始发城市或火车站名", required = true)
	@NotBlank(message = "始发城市不能为空")
	private String startCityOrStation;

	@ApiModelProperty(value = "终点城市或火车站名", required = true)
	@NotBlank(message = "终点城市不能为空")
	private String endCityOrStation;

	@ApiModelProperty(value = "用户出发时间，只需要年月日", required = true)
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "日期格式不正确")
	private String leaveYearMonthDay;
}

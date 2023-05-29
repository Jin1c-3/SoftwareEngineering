package com.yutech.back.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel(value = "火车票座位查询对象", description = "火车票座位查询")
public class TrainSeatDTO {

	@ApiModelProperty(value = "座位类型")
	@NotBlank(message = "座位类型不能为空")
	private String seatType;

	@ApiModelProperty(value = "车次")
	@NotBlank(message = "车次不能为空")
	private String trainNumberId;

	@ApiModelProperty(value = "日期")
	@NotBlank(message = "日期不能为空")
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "日期格式不正确")
	private String day;

	@ApiModelProperty(value = "出发站")
	@NotBlank(message = "出发站不能为空")
	private String startStation;

	@ApiModelProperty(value = "到达站")
	@NotBlank(message = "到达站不能为空")
	private String endStation;
}

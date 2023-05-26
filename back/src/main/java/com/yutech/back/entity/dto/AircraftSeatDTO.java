package com.yutech.back.entity.dto;

import com.yutech.back.common.utils.RegexUtil;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel(value = "飞机票座位查询对象", description = "飞机票座位查询")
public class AircraftSeatDTO {

	@NotBlank(message = "航班号不能为空")
	private String flightNo;

	@NotBlank(message = "站点不能为空，数值为下面六者之一：1、2、3、12、23、123")
	private Integer stationOrder;

	private String seatNo;

	private String seatType;

	@NotBlank(message = "日期不能为空")
	@Pattern(regexp = RegexUtil.DATE_Y4M2D2, message = "日期格式不正确")
	private String date;
}

package com.yutech.back.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "支付传输层", description = "支付传输层")
public class PaymentDTO {

	@ApiModelProperty(value = "金额")
	private BigDecimal money;

	@ApiModelProperty(value = "交通工具类型")
	private String vehicleType;

	@ApiModelProperty(value = "用户ID")
	private String usrId;

//	@ApiModelProperty(value = "起始地")
//	String from;
//
//	@ApiModelProperty(value = "目的地")
//	String to;
//
	@ApiModelProperty(value = "出发时间")
	String dueDate;

	@ApiModelProperty(value = "座位类型")
	String seatType;

	@ApiModelProperty(value = "航班号或者车次号")
	String flightOrTrainNO;

	@ApiModelProperty(value = "乘客姓名")
	String passengerName;
}

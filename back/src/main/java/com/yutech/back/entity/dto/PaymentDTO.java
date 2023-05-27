package com.yutech.back.entity.dto;

import com.yutech.back.common.utils.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@ApiModel(value = "支付传输层", description = "支付传输层")
public class PaymentDTO {

	@ApiModelProperty(value = "交通工具类型")
	@NotBlank(message = "交通工具类型不能为空")
	private String vehicleType;

	@ApiModelProperty(value = "航班号或者车次号")
	@NotBlank(message = "航班号或者车次号不能为空")
	String flightOrTrainNO;

	@ApiModelProperty(value = "起始时间")
	@Pattern(regexp = RegexUtil.DATETIME_Y4M2D2H2M2, message = "时间格式不正确")
	@NotBlank(message = "起始时间不能为空")
	String startTime;

	@ApiModelProperty(value = "终止时间")
	@Pattern(regexp = RegexUtil.DATETIME_Y4M2D2H2M2, message = "时间格式不正确")
	@NotBlank(message = "终止时间不能为空")
	String endTime;

	@ApiModelProperty(value = "起始地")
	@NotBlank(message = "起始地不能为空")
	String startPortOrStation;

	@ApiModelProperty(value = "目的地")
	@NotBlank(message = "目的地不能为空")
	String endPortOrStation;

	@ApiModelProperty(value = "乘客姓名")
	@NotBlank(message = "乘客姓名不能为空")
	String passengerName;

	@ApiModelProperty(value = "座位类型")
	@NotBlank(message = "座位类型不能为空")
	String seatType;

	@ApiModelProperty(value = "金额")
	@NotBlank(message = "金额不能为空")
	private BigDecimal money;

	@ApiModelProperty(value = "用户ID")
	@NotBlank(message = "用户ID不能为空")
	private String usrId;

}

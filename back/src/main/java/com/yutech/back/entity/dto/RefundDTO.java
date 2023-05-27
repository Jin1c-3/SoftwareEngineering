package com.yutech.back.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@ApiModel(value = "退款传输层", description = "退款传输层")
public class RefundDTO {

	@ApiModelProperty(value = "退款理由")
	private String reason;

	@ApiModelProperty(value = "退款金额")
	@NotBlank(message = "退款金额不能为空")
	private BigDecimal money;

	@ApiModelProperty(value = "订单编号")
	@NotBlank(message = "订单编号不能为空")
	private String orderId;

}

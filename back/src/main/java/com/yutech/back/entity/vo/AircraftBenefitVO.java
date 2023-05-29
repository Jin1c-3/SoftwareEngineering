package com.yutech.back.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "AircraftBenefit对象", description = "飞机服务商的盈利情况")
public class AircraftBenefitVO {
	private String aircraftId;
	private Integer flightTicketNum;
	private BigDecimal totalBenefit;
	private BigDecimal trueBenefit;
}

package com.yutech.back.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "SuperUsrBenefitVO对象", description = "超级用户的盈利情况")
public class SuperUsrBenefitVO {
	private String vehicleType;
	private Integer ticketNum;
	private BigDecimal totalBenefit;
	private BigDecimal trueBenefit;

	public SuperUsrBenefitVO() {
		totalBenefit = new BigDecimal(0);
		trueBenefit = new BigDecimal(0);
		ticketNum = 0;
	}
}

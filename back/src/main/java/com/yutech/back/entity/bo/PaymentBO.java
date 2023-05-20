package com.yutech.back.entity.bo;

import com.yutech.back.common.validator.group.AddGroup;
import com.yutech.back.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

/**
 * 支付业务对象
 */
@Data
@ApiModel(value = "支付业务对象", description = "支付业务对象")
public class PaymentBO {
	@Null
	@ApiModelProperty(value = "订单号")
	private String orderNO;

	@NotBlank(message = "订单主题描述不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@ApiModelProperty(value = "订单主题描述")
	private String subject;

	@NotBlank(message = "订单金额不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@ApiModelProperty(value = "订单金额")
	private BigDecimal money;

	@ApiModelProperty(value = "交通工具类型")
	private String vehicleType;

	@ApiModelProperty(value = "用户ID")
	private String usrId;

	/**
	 * 有参构造器
	 */
	public PaymentBO(@org.jetbrains.annotations.Nullable String orderNO, String subject, BigDecimal money) {
		this.orderNO = orderNO;
		this.subject = subject;
		this.money = money;
	}
}

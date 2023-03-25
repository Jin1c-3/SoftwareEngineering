package com.yutech.back.bo;

import com.yutech.back.common.validator.group.AddGroup;
import com.yutech.back.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * 支付业务对象
 */
@Data
public class PaymentBO {
	@Nullable
	@ApiModelProperty(value = "订单号")
	private String orderNO;

	@NotBlank(message = "订单主题描述不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@ApiModelProperty(value = "订单主题描述")
	private String subject;

	@NotBlank(message = "订单金额不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@ApiModelProperty(value = "订单金额")
	private BigDecimal money;

	/**
	 * 有参构造器
	 */
	public PaymentBO(String orderNO, String subject, BigDecimal money) {
		this.orderNO = orderNO;
		this.subject = subject;
		this.money = money;
	}
}

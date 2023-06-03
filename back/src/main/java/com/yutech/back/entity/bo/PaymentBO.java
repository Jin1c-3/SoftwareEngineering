package com.yutech.back.entity.bo;

import com.yutech.back.common.validator.group.AddGroup;
import com.yutech.back.common.validator.group.UpdateGroup;
import com.yutech.back.entity.dto.PaymentDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * 支付业务对象
 */
@Data
@ApiModel(value = "支付业务对象", description = "支付业务对象")
public class PaymentBO {

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

	public PaymentBO() {
	}

	/**
	 * 有参构造器
	 */
	public PaymentBO(PaymentDTO paymentDTO) {
		this.subject = paymentDTO.getVehicleType() + " "
				+ paymentDTO.getFlightOrTrainNO() + " "
				+ paymentDTO.getStartPortOrStation() + " - "
				+ paymentDTO.getEndPortOrStation();
		this.money = paymentDTO.getMoney();
		this.vehicleType = paymentDTO.getVehicleType();
		this.usrId = paymentDTO.getUsrId();
	}
}

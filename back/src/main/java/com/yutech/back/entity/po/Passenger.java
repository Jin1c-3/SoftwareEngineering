package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Passenger对象", description = "乘客表")
public class Passenger implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableField("usr_ID")
	private String usrId;

	@TableField("passenger_name")
	private String passengerName;

	@TableField("passenger_ID")
	private String passengerId;

	@TableField("phone_number")
	private String phoneNumber;

	@TableField("passenger_type")
	private String passengerType;


}

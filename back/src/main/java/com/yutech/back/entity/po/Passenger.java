package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * @since 2023-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Passenger对象", description = "")
public class Passenger implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户ID", required = true)
	@TableId("usr_ID")
	private String usrId;

	@ApiModelProperty(value = "乘客姓名")
	@TableField("passenger_name")
	private String passengerName;

	@ApiModelProperty(value = "身份证号", required = true)
	@TableId("passenger_ID")
	private String passengerId;

	@ApiModelProperty(value = "电话号码")
	@TableField("phone_number")
	private String phoneNumber;

	@ApiModelProperty(value = "乘客类型（飞机，火车）")
	@TableField("passenger_type")
	private String passengerType;


}

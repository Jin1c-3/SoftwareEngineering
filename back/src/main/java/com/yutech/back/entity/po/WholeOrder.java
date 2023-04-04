package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@ApiModel(value = "WholeOrder对象", description = "")
public class WholeOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "order_ID", type = IdType.AUTO)
	private Integer orderId;

	@TableField("order_time")
	private LocalDateTime orderTime;

	@ApiModelProperty(value = "是否已支付")
	@TableField("order_flag")
	private String orderFlag;

	@TableField("vehicle_type")
	private String vehicleType;

	@TableField("usr_ID")
	private String usrId;


}

package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
@ApiModel(value = "WholeOrder对象", description = "")
public class WholeOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "order_ID")
	private String orderId;

	@TableField("order_time")
	private String orderTime;

	@ApiModelProperty(value = "订单状态")
	@TableField("order_status")
	private String orderStatus;

	@TableField("vehicle_type")
	private String vehicleType;

	@ApiModelProperty(value = "用户ID", required = true)
	@TableField("usr_ID")
	private String usrId;

	/**
	 * 有参构造器
	 */
	public WholeOrder(String orderNO, String orderStatus, String vehicleType, String usrId) {
		this.orderId = orderNO;
		this.orderTime = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(LocalDateTime.now());
		this.orderStatus = orderStatus;
		this.vehicleType = vehicleType;
		this.usrId = usrId;
	}

//	public WholeOrder(String orderNO, LocalDateTime date, String orderFlag, String vehicleType, String usrId) {
//		this.orderId = orderNO;
//		this.orderTime = date;
//		this.orderFlag = orderFlag;
//		this.vehicleType = vehicleType;
//		this.usrId = usrId;
//	}
}

package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@ApiModel(value = "FlightInfoDetail对象", description = "")
public class FlightInfoDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableField("flight_start_time")
	private LocalDateTime flightStartTime;

	@TableField("flight_end_time")
	private LocalDateTime flightEndTime;

	@TableId("flight_ID")
	private String flightId;

	@TableField("flight_start_city")
	private String flightStartCity;

	@TableField("flight_end_city")
	private String flightEndCity;

	@TableField("flight_start_port")
	private String flightStartPort;

	@TableField("flight_end_port")
	private String flightEndPort;

	@TableId("flight_order")
	private Integer flightOrder;

	@ApiModelProperty(value = "经济舱本段价格")
	@TableField("flight_L_price")
	private BigDecimal flightLPrice;

	@ApiModelProperty(value = "商务舱本段价格")
	@TableField("flight_M_price")
	private BigDecimal flightMPrice;

	@ApiModelProperty(value = "头等舱本段价格")
	@TableField("flight_T_price")
	private BigDecimal flightTPrice;


}

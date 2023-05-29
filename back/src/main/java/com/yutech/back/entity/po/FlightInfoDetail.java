package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

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
	@MppMultiId
	@TableField("flight_ID")
	private String flightId;

	@MppMultiId
	@TableField("flight_order")
	private Integer flightOrder;

	@MppMultiId
	@TableField("flight_schedule")
	private String flightSchedule;

	@TableField("flight_start_time")
	private String flightStartTime;

	@TableField("flight_end_time")
	private String flightEndTime;

	@TableField("flight_start_city")
	private String flightStartCity;

	@TableField("flight_end_city")
	private String flightEndCity;

	@TableField("flight_start_port")
	private String flightStartPort;

	@TableField("flight_end_port")
	private String flightEndPort;

	@ApiModelProperty(value = "经济舱本段价格")
	@TableField("flight_L_price")
	private BigDecimal flightLPrice;

	@ApiModelProperty(value = "商务舱本段价格")
	@TableField("flight_M_price")
	private BigDecimal flightMPrice;

	@ApiModelProperty(value = "头等舱本段价格")
	@TableField("flight_T_price")
	private BigDecimal flightTPrice;

	@TableField("aircraft_ID")
	private String aircraftId;

	@TableField("aircraft_type")
	private String aircraftType;

	@TableField("flight_status")
	private String flightStatus;


}

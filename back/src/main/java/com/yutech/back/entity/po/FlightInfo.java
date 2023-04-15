package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
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
@ApiModel(value = "FlightInfo对象", description = "")
public class FlightInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "航班班期，即1..4..7")
	@TableField("flight_schedule")
	private String flightSchedule;

	@MppMultiId
	@TableField("flight_ID")
	private String flightId;

	@ApiModelProperty(value = "总起点")
	@TableField("flight_start_city")
	private String flightStartCity;

	@ApiModelProperty(value = "总终点")
	@TableField("flight_end_city")
	private String flightEndCity;

	@ApiModelProperty(value = "总起点")
	@TableField("flight_start_port")
	private String flightStartPort;

	@ApiModelProperty(value = "总终点")
	@TableField("flight_end_port")
	private String flightEndPort;

	@ApiModelProperty(value = "航班状态")
	@TableField("flight_status")
	private String flightStatus;

	@TableField("aircraft_type")
	private String aircraftType;

	@ApiModelProperty(value = "是否直飞")
	@TableField("direct_flag")
	private String directFlag;

	@MppMultiId
	@TableField("aircraft_ID")
	private String aircraftId;


}

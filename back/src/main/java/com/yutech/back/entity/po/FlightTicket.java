package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yutech.back.entity.dto.PaymentDTO;
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
@ApiModel(value = "FlightTicket对象", description = "飞机机票存储")
public class FlightTicket implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "ticket_ID", type = IdType.AUTO)
	private Integer ticketId;

	@TableField("flight_ID")
	private String flightId;

	@TableField("order_ID")
	private String orderId;

	@ApiModelProperty(value = "总起点")
	@TableField("flight_start_time")
	private String flightStartTime;

	@ApiModelProperty(value = "总终点")
	@TableField("flight_end_time")
	private String flightEndTime;

	@ApiModelProperty(value = "总起点")
	@TableField("flight_start_port")
	private String flightStartPort;

	@ApiModelProperty(value = "总终点")
	@TableField("flight_end_port")
	private String flightEndPort;

	@TableField("ticket_status")
	private String ticketStatus;

	@TableField("passenger_name")
	private String passengerName;

	@TableField("seat_type")
	private String seatType;

	@TableField("seat_no")
	private String seatNo;

	@TableField("flight_price")
	private BigDecimal flightPrice;

	public FlightTicket() {
	}

	public FlightTicket(PaymentDTO paymentDTO) {
		this.flightId = paymentDTO.getFlightOrTrainNO();
		this.flightStartTime = paymentDTO.getStartTime();
		this.flightEndTime = paymentDTO.getEndTime();
		this.flightStartPort = paymentDTO.getStartPortOrStation();
		this.flightEndPort = paymentDTO.getEndPortOrStation();
		this.passengerName = paymentDTO.getPassengerName();
		this.seatType = paymentDTO.getSeatType();
		this.seatNo = paymentDTO.getSeatNo();
		this.flightPrice = paymentDTO.getMoney();
	}

}

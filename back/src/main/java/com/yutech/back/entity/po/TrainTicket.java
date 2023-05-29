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
@ApiModel(value = "TrainTicket对象", description = "")
public class TrainTicket implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	@TableId(value = "train_ticket_ID", type = IdType.AUTO)
	private Integer trainTicketId;

	@TableField("train_number_ID")
	private String trainNumberId;

	@ApiModelProperty(value = "订单编号  自增")
	@TableField("order_ID")
	private String orderId;

	@TableField("train_ticket_start_time")
	private String trainTicketStartTime;

	@TableField("train_ticket_end_time")
	private String trainTicketEndTime;

	@TableField("train_start_station")
	private String trainStartStation;

	@TableField("train_end_station")
	private String trainEndStation;

	@TableField("ticket_status")
	private String ticketStatus;

	@TableField("passenger_name")
	private String passengerName;

	@TableField("seat_type")
	private String seatType;

	@TableField("seat_no")
	private String seatNo;

	@TableField("train_price")
	private BigDecimal trainPrice;

	public TrainTicket() {
	}

	public TrainTicket(PaymentDTO paymentDTO) {
		this.trainNumberId = paymentDTO.getFlightOrTrainNO();
		this.trainTicketStartTime = paymentDTO.getStartTime();
		this.trainTicketEndTime = paymentDTO.getEndTime();
		this.trainStartStation = paymentDTO.getStartPortOrStation();
		this.trainEndStation = paymentDTO.getEndPortOrStation();
		this.passengerName = paymentDTO.getPassengerName();
		this.seatType = paymentDTO.getSeatType();
		this.trainPrice = paymentDTO.getMoney();
//		this.seatNo = paymentDTO.getSeatNo();
	}

}

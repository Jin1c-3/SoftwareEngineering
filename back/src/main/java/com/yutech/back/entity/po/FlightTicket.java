package com.yutech.back.entity.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="FlightTicket对象", description="")
public class FlightTicket implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "ticket_ID", type = IdType.AUTO)
    private Integer ticketId;

    @TableField("flight_ID")
    private String flightId;

    @TableField("order_ID")
    private Integer orderId;

    @ApiModelProperty(value = "总起点")
    @TableField("flight_start_time")
    private LocalDateTime flightStartTime;

    @ApiModelProperty(value = "总终点")
    @TableField("flight_end_time")
    private LocalDateTime flightEndTime;

    @TableField("ticket_status")
    private String ticketStatus;

    @TableField("passenger_name")
    private String passengerName;

    @TableField("seat_type")
    private String seatType;

    @ApiModelProperty(value = "总起点")
    @TableField("flight_start_port")
    private String flightStartPort;

    @ApiModelProperty(value = "总终点")
    @TableField("flight_end_port")
    private String flightEndPort;

    @TableField("flight_price")
    private BigDecimal flightPrice;


}
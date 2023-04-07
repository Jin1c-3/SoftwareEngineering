package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
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
 * @since 2023-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TrainTicket对象", description="")
public class TrainTicket implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "train_ticket_ID", type = IdType.AUTO)
    private Integer trainTicketId;

    @TableField("train_number_ID")
    private String trainNumberId;

    @TableField("order_ID")
    private Integer orderId;

    @TableField("train_ticket_start_time")
    private LocalDateTime trainTicketStartTime;

    @TableField("train_ticket_end_time")
    private LocalDateTime trainTicketEndTime;

    @TableField("ticket_status")
    private String ticketStatus;

    @TableField("passenger_name")
    private String passengerName;

    @TableField("seat_type")
    private String seatType;

    @TableField("train_start_station")
    private String trainStartStation;

    @TableField("train_end_station")
    private String trainEndStation;

    @TableField("train_price")
    private Integer trainPrice;


}

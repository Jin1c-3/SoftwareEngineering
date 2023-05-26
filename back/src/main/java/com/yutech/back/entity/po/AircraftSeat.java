package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
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
 * @since 2023-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AircraftSeat对象", description="")
public class AircraftSeat implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("flight_no")
    private String flightNo;

    @TableField("station_order")
    private Integer stationOrder;

    @TableField("seat_no")
    private String seatNo;

    @TableField("seat_type")
    private String seatType;

    @TableField("date")
    private String date;

    @TableField("seat_status")
    private String seatStatus;


}

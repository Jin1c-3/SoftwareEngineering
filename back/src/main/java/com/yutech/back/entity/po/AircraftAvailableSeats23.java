package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yutech.back.entity.bo.AircraftAvailableSeats;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="AircraftAvailableSeats23对象", description="")
public class AircraftAvailableSeats23 extends AircraftAvailableSeats implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("flight_no")
    private String flightNo;

    @TableField("seat_no")
    private String seatNo;

    @TableField("seat_type")
    private String seatType;

    @TableField("date")
    private String date;


}

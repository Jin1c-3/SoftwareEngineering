package com.yutech.back.entity.po;

import java.time.LocalDate;
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
 * @since 2023-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AircraftAvailableSeats23对象", description="")
public class AircraftAvailableSeats23 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("flight_no")
    private String flightNo;

    @TableField("seat_no")
    private String seatNo;

    @TableField("seat_type")
    private String seatType;

    @TableField("date")
    private LocalDate date;


}

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
@ApiModel(value="TrainNumberInfoDetail对象", description="")
public class TrainNumberInfoDetail implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "到达该站时间")
    @TableField("train_arrive_time")
    private LocalDateTime trainArriveTime;

    @ApiModelProperty(value = "离开该站时间")
    @TableField("train_leave_time")
    private LocalDateTime trainLeaveTime;

    @ApiModelProperty(value = "车次编号")
    @TableId("train_number_ID")
    private String trainNumberId;

    @ApiModelProperty(value = "到达站点所在城市")
    @TableField("train_arrive_city")
    private String trainArriveCity;

    @ApiModelProperty(value = "到达站点名")
    @TableField("train_arrive_station")
    private String trainArriveStation;

    @ApiModelProperty(value = "站序")
    @TableId("train_order")
    private Integer trainOrder;

    @ApiModelProperty(value = "里程")
    @TableField("mileage")
    private Float mileage;

    @ApiModelProperty(value = "硬卧/一等座票价")
    @TableField("train_one_price")
    private BigDecimal trainOnePrice;

    @ApiModelProperty(value = "硬座/二等座票价")
    @TableField("train_two_price")
    private BigDecimal trainTwoPrice;

    @ApiModelProperty(value = "软座/特等座票价")
    @TableField("train_sp_price")
    private BigDecimal trainSpPrice;

    @ApiModelProperty(value = "软卧票价")
    @TableField("train_ssp_price")
    private BigDecimal trainSspPrice;

    @ApiModelProperty(value = "相对日期")
    @TableField("dateorder")
    private Integer dateorder;


}

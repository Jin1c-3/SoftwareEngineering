package com.yutech.back.entity.po;

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
@ApiModel(value="TrainNumberDetails对象", description="")
public class TrainNumberDetails implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "到达该站时间")
    @TableField("train_arrive_time")
    private LocalDateTime trainArriveTime;

    @ApiModelProperty(value = "离开该站时间")
    @TableField("train_leave_time")
    private LocalDateTime trainLeaveTime;

    @ApiModelProperty(value = "车次编号")
    @TableField("train_number_ID")
    private String trainNumberId;

    @ApiModelProperty(value = "到达站点所在城市")
    @TableField("train_arrive_city")
    private String trainArriveCity;

    @ApiModelProperty(value = "到达站点名")
    @TableField("train_arrive_station")
    private String trainArriveStation;

    @ApiModelProperty(value = "站序")
    @TableField("train_order")
    private Integer trainOrder;

    @ApiModelProperty(value = "里程")
    @TableField("mileage")
    private Integer mileage;

    @ApiModelProperty(value = "硬卧/一等座票价")
    @TableField("train_one_price")
    private Integer trainOnePrice;

    @ApiModelProperty(value = "硬座/二等座票价")
    @TableField("train_two_price")
    private Integer trainTwoPrice;

    @ApiModelProperty(value = "软座/特等座票价")
    @TableField("train_sp_price")
    private Integer trainSpPrice;

    @ApiModelProperty(value = "软卧票价")
    @TableField("train_ssp_price")
    private Integer trainSspPrice;

    @ApiModelProperty(value = "相对日期")
    @TableField("dateorder")
    private Integer dateorder;


}

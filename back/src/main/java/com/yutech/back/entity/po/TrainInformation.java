package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="TrainInformation对象", description="")
public class TrainInformation implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "火车编号")
    @TableId("train_ID")
    private String trainId;

    @ApiModelProperty(value = "火车类型")
    @TableField("train_type")
    private String trainType;

    @ApiModelProperty(value = "硬卧/一等座数量")
    @TableField("train_one_num")
    private Integer trainOneNum;

    @ApiModelProperty(value = "硬座/二等座数量")
    @TableField("train_two_num")
    private Integer trainTwoNum;

    @ApiModelProperty(value = "软座/特等座数量")
    @TableField("traint_sp_num")
    private Integer traintSpNum;

    @ApiModelProperty(value = "软卧数量")
    @TableField("train_ssp_num")
    private Integer trainSspNum;

    @TableField("train_status")
    private String trainStatus;

    @ApiModelProperty(value = "车厢总数")
    @TableField("train_railway_carriage_num")
    private Integer trainRailwayCarriageNum;


}

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
@ApiModel(value="RailwayCarriageInformation对象", description="")
public class RailwayCarriageInformation implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("train_type")
    private String trainType;

    @TableField("one_railway_carriage_seat_num")
    private Integer oneRailwayCarriageSeatNum;

    @TableField("railway_carriage_type")
    private String railwayCarriageType;

    @TableField("railway_carriage_ID")
    private Integer railwayCarriageId;

    @TableField("each_row_num")
    private Integer eachRowNum;


}

package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * @since 2023-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "RailwayCarriageInfo对象", description = "")
public class RailwayCarriageInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "列车类型")
	@MppMultiId
	@TableField("train_type")
	private String trainType;

	@ApiModelProperty(value = "座位数")
	@TableField("one_railway_carriage_seat_num")
	private Integer oneRailwayCarriageSeatNum;

	@ApiModelProperty(value = "车厢类型")
	@TableField("railway_carriage_type")
	private String railwayCarriageType;

	@ApiModelProperty(value = "车厢号")
	@MppMultiId
	@TableField("railway_carriage_ID")
	private Integer railwayCarriageId;

	@ApiModelProperty(value = "每排座位数")
	@TableField("each_row_num")
	private Integer eachRowNum;


}

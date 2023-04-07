package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * @since 2023-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "TrainNumberInformation对象", description = "车次表")
public class TrainNumberInformation implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId("train_number_ID")
	@ApiModelProperty(value = "车次")
	private String trainNumberId;

	@TableField("train_ID")
	private String trainId;

	@ApiModelProperty(value = "始发城市")
	@TableField("train_start_city")
	private String trainStartCity;

	@ApiModelProperty(value = "终点城市")
	@TableField("train_end_city")
	private String trainEndCity;

	@ApiModelProperty(value = "始发站")
	@TableField("train_start_station")
	private String trainStartStation;

	@ApiModelProperty(value = "终点站")
	@TableField("train_end_station")
	private String trainEndStation;

	@ApiModelProperty(value = "车次状态，由服务商指定是否停开等")
	@TableField("train_number_status")
	private String trainNumberStatus;

	@ApiModelProperty(value = "始发时间")
	@TableField("train_start_time")
	private LocalDateTime trainStartTime;

	@ApiModelProperty(value = "终点时间")
	@TableField("train_end_time")
	private LocalDateTime trainEndTime;


}
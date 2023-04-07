package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="Aircraft对象", description="")
public class Aircraft implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("aircraft_ID")
    private String aircraftId;

    @TableField("aircraft_type")
    private String aircraftType;

    @ApiModelProperty(value = "头等舱座位数")
    @TableField("aircraft_T_num")
    private Integer aircraftTNum;

    @ApiModelProperty(value = "商务舱座位数")
    @TableField("aircraft_M_num")
    private Integer aircraftMNum;

    @ApiModelProperty(value = "经济舱座位数")
    @TableField("aircraft_L_num")
    private Integer aircraftLNum;

    @ApiModelProperty(value = "可用/不可用")
    @TableField("aircraft_status")
    private String aircraftStatus;

    @TableField("service_provider_ID")
    private Integer serviceProviderId;


}

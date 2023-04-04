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
@ApiModel(value="Aircraft对象", description="")
public class Aircraft implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("aircraft_ID")
    private String aircraftId;

    @TableField("aircraft_type")
    private String aircraftType;

    @TableField("aircraft_T_num")
    private Integer aircraftTNum;

    @TableField("aircraft_M_num")
    private Integer aircraftMNum;

    @TableField("aircraft_L_num")
    private Integer aircraftLNum;

    @TableField("aircraft_status")
    private String aircraftStatus;

    @TableField("service_provider_ID")
    private Integer serviceProviderId;


}

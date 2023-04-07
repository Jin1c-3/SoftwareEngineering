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
 * @since 2023-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AbnormalRecord对象", description="")
public class AbnormalRecord implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "取消的航班")
    @TableId("flight_ID")
    private String flightId;

    @ApiModelProperty(value = "取消航班的那天")
    @TableField("abnormal_data")
    private LocalDateTime abnormalData;


}

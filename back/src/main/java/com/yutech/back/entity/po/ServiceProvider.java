package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

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
@ApiModel(value="ServiceProvider对象", description="")
public class ServiceProvider implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "service_provider_ID", type = IdType.AUTO)
    private Integer serviceProviderId;

    @TableField("service_provider_pwd")
    private String serviceProviderPwd;

    @TableField("service_provider_name")
    private String serviceProviderName;

    @ApiModelProperty(value = "约定提成")
    @TableField("push_money")
    private BigDecimal pushMoney;


}

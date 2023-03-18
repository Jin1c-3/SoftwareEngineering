package com.yutech.back.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2023-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("CardInfo")
@ApiModel(value="CardInfo对象", description="")
public class CardInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("CardNo")
    private String CardNo;

    @TableField("Reader")
    private String Reader;

    @TableField("WorkPlace")
    private String WorkPlace;

    @TableField("IDCard")
    private String IDCard;

    @TableField("CTypeID")
    private String CTypeID;

    @TableField("CreateDate")
    private LocalDateTime CreateDate;

    @TableField("CardState")
    private Integer CardState;


}

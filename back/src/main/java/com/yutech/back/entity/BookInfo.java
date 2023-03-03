package com.yutech.back.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2023-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("BookInfo")
@ApiModel(value="BookInfo对象", description="")
public class BookInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("BookNo")
    private String BookNo;

    @TableField("BookName")
    private String BookName;

    @TableField("Author")
    private String Author;

    @TableField("Publisher")
    private String Publisher;

    @TableField("Price")
    private Float Price;

    @TableField("TypeID")
    private Integer TypeID;

    @TableField("CoverPhoto")
    private byte[] CoverPhoto;

    @TableField("Memo")
    private String Memo;


}

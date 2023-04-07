package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="SuperUsr对象", description="")
public class SuperUsr implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("super_usr_ID")
    private String superUsrId;

    @TableField("super_usr_name")
    private String superUsrName;

    @TableField("super_usr_pwd")
    private String superUsrPwd;


}

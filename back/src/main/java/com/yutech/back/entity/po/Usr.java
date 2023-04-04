package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

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
@ApiModel(value = "Usr对象", description = "")
public class Usr implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "头像文件，不会存储到数据库")
	@TableField(exist = false)
	private MultipartFile avatar;

	@TableId("usr_account")
	private String usrAccount;

	@TableField("usr_ID")
	private String usrId;

	@TableField("usr_email")
	private String usrEmail;

	@ApiModelProperty(value = "头像地址，会存储到数据库")
	@TableField("usr_avatar")
	private String usrAvatar;

	@TableField("usr_viplevel")
	private Integer usrViplevel;

	@TableField("usr_pwd")
	private String usrPwd;

	@TableField("usr_phone")
	private String usrPhone;

}

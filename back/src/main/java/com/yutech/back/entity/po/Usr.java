package com.yutech.back.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yutech.back.entity.dto.UsrDTO;
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
@ApiModel(value = "Usr对象", description = "Usr持久层")
public class Usr implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户账号", required = true)
	@TableField("usr_account")
	private String usrAccount;

	@ApiModelProperty(value = "用户ID")
	@TableId("usr_ID")
	private String usrId;

	@TableField("usr_email")
	private String usrEmail;

	@ApiModelProperty(value = "头像网址，会存储到数据库")
	@TableField("usr_avatar")
	private String usrAvatar;

	@TableField("usr_vip_level")
	private Integer usrVipLevel;

	@ApiModelProperty(value = "用户密码", required = true)
	@TableField("usr_pwd")
	private String usrPwd;

	@TableField("usr_phone")
	private String usrPhone;

	/**
	 * 无参构造方法
	 */
	public Usr() {
	}

	/**
	 * 单参构造方法
	 *
	 * @param usrDTO 用户信息DTO
	 */
	public Usr(UsrDTO usrDTO) {
		this.usrAccount = usrDTO.getUsrAccount();
		this.usrId = usrDTO.getUsrId();
		this.usrEmail = usrDTO.getUsrEmail();
		this.usrVipLevel = usrDTO.getUsrVipLevel();
		this.usrPwd = usrDTO.getUsrPwd();
		this.usrPhone = usrDTO.getUsrPhone();
	}

}

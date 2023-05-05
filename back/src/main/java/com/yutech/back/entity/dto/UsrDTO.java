package com.yutech.back.entity.dto;

import com.yutech.back.entity.po.Usr;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户信息表现层
 */
@Data
@ApiModel(value = "用户信息表现层", description = "用户信息表现层")
public class UsrDTO {
	@ApiModelProperty(value = "用户账号")
	private String usrAccount;

	@ApiModelProperty(value = "用户ID", required = true)
	private String usrId;

	private String usrEmail;

	private Integer usrVipLevel;

	@ApiModelProperty(value = "用户密码")
	private String usrPwd;

	private String usrPhone;

	/**
	 * 无参构造方法
	 */
	public UsrDTO() {
	}

	/**
	 * 单参构造方法
	 *
	 * @param usr 用户信息PO
	 */
	public UsrDTO(Usr usr) {
		this.usrAccount = usr.getUsrAccount();
		this.usrId = usr.getUsrId();
		this.usrEmail = usr.getUsrEmail();
		this.usrVipLevel = usr.getUsrVipLevel();
		this.usrPhone = usr.getUsrPhone();
	}
}

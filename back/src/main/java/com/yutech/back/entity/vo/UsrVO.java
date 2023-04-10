package com.yutech.back.entity.vo;

import com.yutech.back.entity.po.Usr;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户信息表现层，无密码，有token", description = "用户信息表现层")
public class UsrVO {

	@ApiModelProperty(value = "用户账号")
	private String usrAccount;

	@ApiModelProperty(value = "用户ID")
	private String usrId;

	private String usrEmail;

	@ApiModelProperty(value = "头像网址，会存储到数据库")
	private String usrAvatar;

	private Integer usrVipLevel;

	private String usrPhone;

	@ApiModelProperty(value = "token")
	private String token;

	/**
	 * 无参构造方法
	 */
	public UsrVO() {
	}

	/**
	 * 单参构造方法
	 *
	 * @param usr 用户信息PO
	 */
	public UsrVO(Usr usr) {
		this.usrAccount = usr.getUsrAccount();
		this.usrId = usr.getUsrId();
		this.usrEmail = usr.getUsrEmail();
		this.usrAvatar = usr.getUsrAvatar();
		this.usrVipLevel = usr.getUsrVipLevel();
		this.usrPhone = usr.getUsrPhone();
	}

	/**
	 * 双参构造方法
	 *
	 * @param usr   用户信息PO
	 * @param token 用户token
	 */
	public UsrVO(Usr usr, String token) {
		this.usrAccount = usr.getUsrAccount();
		this.usrId = usr.getUsrId();
		this.usrEmail = usr.getUsrEmail();
		this.usrAvatar = usr.getUsrAvatar();
		this.usrVipLevel = usr.getUsrVipLevel();
		this.usrPhone = usr.getUsrPhone();
		this.token = token;
	}
}

package com.yutech.back.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户登录信息传输层对象", description = "用户登录信息传输层对象")
public class UsrLoginDTO {

	@ApiModelProperty(value = "用户名", required = true)
	private String usrName;

	@ApiModelProperty(value = "用户密码", required = true)
	private String usrPassword;
}

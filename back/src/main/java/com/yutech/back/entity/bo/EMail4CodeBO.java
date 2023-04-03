package com.yutech.back.entity.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "邮箱验证码业务对象", description = "邮箱验证码业务对象")
public class EMail4CodeBO {

	@NotBlank(message = "邮箱地址不能为空")
	@ApiModelProperty(value = "邮箱地址")
	private String to;

	@ApiModelProperty(value = "验证码")
	private String code;
}

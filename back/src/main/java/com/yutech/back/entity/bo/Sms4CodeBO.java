package com.yutech.back.entity.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "短信验证码业务对象", description = "短信验证码业务对象")
public class Sms4CodeBO {
	@NotBlank(message = "手机号不能为空")
	@ApiModelProperty(value = "手机号")
	private String phone;

	@ApiModelProperty(value = "验证码")
	private String code;
}

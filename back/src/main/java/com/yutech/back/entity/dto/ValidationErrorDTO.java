package com.yutech.back.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "异常信息表现层")
public class ValidationErrorDTO {
	@ApiModelProperty(value = "异常信息field")
	private String field;
	@ApiModelProperty(value = "异常信息defaultMessage")
	private String defaultMessage;

	/**
	 * 有参构造方法
	 */
	public ValidationErrorDTO(String field, String defaultMessage) {
		this.field = field;
		this.defaultMessage = defaultMessage;
	}
}

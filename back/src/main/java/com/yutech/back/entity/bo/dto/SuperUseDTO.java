package com.yutech.back.entity.bo.dto;

import com.yutech.back.entity.po.SuperUsr;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "管理员信息表现层", description = "管理员信息表现层")
public class SuperUseDTO {
	@ApiModelProperty(value = "管理员信息PO")
	private SuperUsr superUsr;
	@ApiModelProperty(value = "管理员token")
	private String token;

	/**
	 * 无参构造方法
	 */
	public SuperUseDTO() {
	}

	/**
	 * 单参构造方法
	 *
	 * @param superUsr 管理员信息PO
	 */
	public SuperUseDTO(SuperUsr superUsr) {
		this.superUsr = superUsr;
	}

	/**
	 * 双参构造方法
	 *
	 * @param superUsr 管理员信息PO
	 * @param token    管理员token
	 */
	public SuperUseDTO(SuperUsr superUsr, String token) {
		this.superUsr = superUsr;
		this.token = token;
	}
}

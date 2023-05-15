package com.yutech.back.entity.vo;

import com.yutech.back.entity.po.SuperUsr;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "管理员信息表现层，无密码，有token", description = "管理员信息表现层")
public class SuperUsrVO {
	@ApiModelProperty(value = "管理员ID", required = true)
	private String superUsrId;

	@ApiModelProperty(value = "管理员名称", required = true)
	private String superUsrName;

	/**
	 * 无参构造方法
	 */
	public SuperUsrVO() {
	}

	/**
	 * 有参构造方法
	 *
	 * @param superUsr 管理员信息
	 */
	public SuperUsrVO(SuperUsr superUsr) {
		this.superUsrId = superUsr.getSuperUsrId();
		this.superUsrName = superUsr.getSuperUsrName();
	}

}

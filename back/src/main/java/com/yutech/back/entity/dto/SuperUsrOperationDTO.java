package com.yutech.back.entity.dto;

import com.yutech.back.entity.po.SuperUsr;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "管理员表操作表现层", description = "管理员表操作表现层")
public class SuperUsrOperationDTO {
	@ApiModelProperty(value = "管理员表操作发起人", required = true)
	private SuperUsr requestMaker;
	@ApiModelProperty(value = "管理员表被操作对象", required = true)
	private SuperUsr requestTarget;
}

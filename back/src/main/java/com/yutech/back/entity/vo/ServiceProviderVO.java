package com.yutech.back.entity.vo;

import com.yutech.back.entity.po.ServiceProvider;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "服务商信息表现层", description = "服务商信息表现层")
public class ServiceProviderVO {

	private Integer serviceProviderId;

	private String serviceProviderName;

	@ApiModelProperty(value = "约定提成")
	private Float pushMoney;

	public ServiceProviderVO() {
	}

	public ServiceProviderVO(ServiceProvider serviceProvider) {
		this.serviceProviderId = serviceProvider.getServiceProviderId();
		this.serviceProviderName = serviceProvider.getServiceProviderName();
		this.pushMoney = serviceProvider.getPushMoney();
	}
}

package com.yutech.back.entity.dto;

import com.yutech.back.entity.po.Usr;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户信息表现层
 */
@Data
@ApiModel(value = "用户信息表现层", description = "用户信息表现层")
public class UsrDTO {
	@ApiModelProperty(value = "用户信息PO")
	private Usr usr;

	@ApiModelProperty(value = "用户token")
	private String token;

	@ApiModelProperty(value = "头像文件，不会存储到数据库")
	private MultipartFile avatar;

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
		this.usr = usr;
	}

	/**
	 * 双参构造方法
	 *
	 * @param usr   用户信息PO
	 * @param token 用户token
	 */
	public UsrDTO(Usr usr, String token) {
		this.usr = usr;
		this.token = token;
	}
}

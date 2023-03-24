package com.yutech.back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yutech.back.common.validator.group.AddGroup;
import com.yutech.back.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * NotEmpty等注释来自JSR303，可以检测前端回传信息的正确性。除此之外还有：
 * Pattern(message = "手机号格式不合法", regexp = "^[1-9]{1}\\d{10}$", groups = {AddGroup.class, UpdateGroup.class})
 * 等多种注释
 * 另外还有逻辑删除的注释：
 * TableLogic(value = "0", delval = "1")
 * ApiModelProperty(value = "逻辑删除标志，0 表示未删除， 1 表示删除") //这个是注释文档
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户 ID")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@ApiModelProperty(value = "用户名")
	private String username;

	@NotBlank(message = "用户密码不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@ApiModelProperty(value = "用户密码")
	private String password;

	private String depart;


}

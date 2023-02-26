package com.yutech.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yutech.back.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-02-24
 */
public interface UserService extends IService<User> {
	public String getPasswordByUsername(String username);
}

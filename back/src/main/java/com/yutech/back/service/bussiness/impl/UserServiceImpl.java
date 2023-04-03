package com.yutech.back.service.bussiness.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yutech.back.entity.User;
import com.yutech.back.mapper.UserMapper;
import com.yutech.back.service.bussiness.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务实现类
 *
 * @author Jin1c-3
 * @since 2023-02-24
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * @param username username应该是数据库中的主键
	 * @return String 返回username对应的password
	 */
	@Override
	public String getPasswordByUsername(String username) {
		log.info("获取用户 " + username + " 的密码");
		return userMapper.selectOne(new QueryWrapper<User>().eq("username", username)).getPassword();
	}

	public List<User> selectAll() {
		log.info("获取所有用户列表");
		return userMapper.selectList(null);
	}
}

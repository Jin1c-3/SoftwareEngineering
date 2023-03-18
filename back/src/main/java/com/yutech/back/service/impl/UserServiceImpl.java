package com.yutech.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yutech.back.entity.User;
import com.yutech.back.mapper.UserMapper;
import com.yutech.back.service.UserService;
import lombok.extern.slf4j.Slf4j;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> 6aeb4a1e6db15a6f65b781cad5d4b29a7c0e77d4
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

<<<<<<< HEAD
	@Autowired
	private UserMapper userMapper;
=======
	UserMapper userMapper;
>>>>>>> 6aeb4a1e6db15a6f65b781cad5d4b29a7c0e77d4

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

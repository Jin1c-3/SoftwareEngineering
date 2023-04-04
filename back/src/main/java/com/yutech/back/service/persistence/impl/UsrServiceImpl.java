package com.yutech.back.service.persistence.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yutech.back.entity.po.Usr;
import com.yutech.back.mapper.po.UsrMapper;
import com.yutech.back.service.persistence.UsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-03
 */
@Service
public class UsrServiceImpl extends ServiceImpl<UsrMapper, Usr> implements UsrService {

	@Autowired
	private UsrMapper usrMapper;

	/**
	 * 验证账号、邮箱、手机号是否唯一
	 *
	 * @param usr 用户信息
	 * @return Boolean
	 */
	@Override
	public Boolean verifyUnique(Usr usr) {
		/**
		 * 1.判断账号是否存在
		 * 2.判断邮箱是否存在
		 * 3.判断手机号是否存在
		 */
		return usrMapper.selectOne(new QueryWrapper<Usr>().eq("usr_account", usr.getUsrAccount())) == null &&
				usrMapper.selectOne(new QueryWrapper<Usr>().eq("usr_email", usr.getUsrEmail())) == null &&
				usrMapper.selectOne(new QueryWrapper<Usr>().eq("usr_phone", usr.getUsrPhone())) == null;
	}
}

package com.yutech.back.service.persistence.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yutech.back.entity.po.Usr;
import com.yutech.back.mapper.po.UsrMapper;
import com.yutech.back.service.persistence.UsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsrServiceImpl extends ServiceImpl<UsrMapper, Usr> implements UsrService {
	@Autowired
	private UsrMapper usrMapper;

	/**
	 * 验证用户信息是否唯一
	 *
	 * @param usr
	 * @return
	 */
	@Override
	public Boolean verifyUnique(Usr usr) {
		return usrMapper.selectOne(new QueryWrapper<Usr>().eq("usr_account", usr.getUsrAccount())) == null &&
				usrMapper.selectOne(new QueryWrapper<Usr>().eq("usr_email", usr.getUsrEmail())) == null &&
				usrMapper.selectOne(new QueryWrapper<Usr>().eq("usr_phone", usr.getUsrPhone())) == null;
	}
}

package com.yutech.back.service.persistence.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yutech.back.entity.po.Usr;
import com.yutech.back.mapper.po.UsrMapper;
import com.yutech.back.service.persistence.UsrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsrServiceImpl extends ServiceImpl<UsrMapper, Usr> implements UsrService {

	@Autowired
	private UsrMapper usrMapper;

	/**
	 * 验证用户信息唯一性
	 *
	 * @param usr 用户信息
	 * @return true:唯一，false:非唯一
	 */
	@Override
	public Boolean verifyUnique(Usr usr) {
		try {
			return usrMapper.selectOne(new QueryWrapper<Usr>().eq("usr_account", usr.getUsrAccount())) == null &&
					usrMapper.selectOne(new QueryWrapper<Usr>().eq("usr_email", usr.getUsrEmail())) == null &&
					usrMapper.selectOne(new QueryWrapper<Usr>().eq("usr_phone", usr.getUsrPhone())) == null;
		} catch (Exception e) {
			log.info("验证用户信息唯一性，此用户非唯一======{}", usr);
			return false;
		}
	}

	/**
	 * 验证更改后用户信息唯一性
	 *
	 * @param usr 用户信息，将此用户的ID排除在外
	 * @return true:唯一，false:非唯一
	 */
	@Override
	public Boolean verifyGoodUpdate(Usr usr) {
		try {
			return usrMapper.selectOne(new QueryWrapper<Usr>().ne("usr_ID", usr.getUsrId()).eq("usr_account", usr.getUsrAccount())) == null &&
					usrMapper.selectOne(new QueryWrapper<Usr>().ne("usr_ID", usr.getUsrId()).eq("usr_email", usr.getUsrEmail())) == null &&
					usrMapper.selectOne(new QueryWrapper<Usr>().ne("usr_ID", usr.getUsrId()).eq("usr_phone", usr.getUsrPhone())) == null;
		} catch (Exception e) {
			log.info("验证更改后用户信息唯一性，此用户非唯一======{}", usr);
			return false;
		}
	}
}

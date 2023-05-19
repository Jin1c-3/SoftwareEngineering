package com.yutech.back.service.persistence.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yutech.back.entity.po.SuperUsr;
import com.yutech.back.mapper.po.SuperUsrMapper;
import com.yutech.back.service.persistence.SuperUsrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-07
 */
@Service
public class SuperUsrServiceImpl extends ServiceImpl<SuperUsrMapper, SuperUsr> implements SuperUsrService {

	@Autowired
	private SuperUsrMapper superUsrMapper;

	@Override
	public Bool mySave(SuperUsr superUsr) {
		return superUsrMapper.mySave(superUsr);
	}
}

package com.yutech.back.service.persistence.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yutech.back.entity.po.Passenger;
import com.yutech.back.entity.po.Usr;
import com.yutech.back.mapper.po.PassengerMapper;
import com.yutech.back.service.persistence.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-03
 */
@Service
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger> implements PassengerService {

	@Autowired
	private PassengerMapper passengerMapper;

	@Override
	public List<Passenger> findPagByUsrId(String usrId) {
		return passengerMapper.selectList(new QueryWrapper<Passenger>().eq("usr_id", usrId));
	}
}

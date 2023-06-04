package com.yutech.back.service.persistence.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yutech.back.entity.po.TrainTicket;
import com.yutech.back.mapper.po.TrainTicketMapper;
import com.yutech.back.service.persistence.TrainTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-07
 */
@Service
public class TrainTicketServiceImpl extends ServiceImpl<TrainTicketMapper, TrainTicket> implements TrainTicketService {

	@Autowired
	public TrainTicketMapper trainTicketMapper;

	public boolean mySave(TrainTicket trainTicket) {
		return trainTicketMapper.mySave(trainTicket);
	}
}

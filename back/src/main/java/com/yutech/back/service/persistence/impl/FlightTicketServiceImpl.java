package com.yutech.back.service.persistence.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yutech.back.entity.po.FlightTicket;
import com.yutech.back.mapper.po.FlightTicketMapper;
import com.yutech.back.service.persistence.FlightTicketService;
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
public class FlightTicketServiceImpl extends ServiceImpl<FlightTicketMapper, FlightTicket> implements FlightTicketService {
	@Autowired
	private FlightTicketMapper flightTicketMapper;

	public boolean mySave(FlightTicket flightTicket) {
		return flightTicketMapper.mySave(flightTicket);
	}
}

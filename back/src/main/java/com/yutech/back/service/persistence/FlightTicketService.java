package com.yutech.back.service.persistence;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yutech.back.entity.po.FlightTicket;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-07
 */
public interface FlightTicketService extends IService<FlightTicket> {

	boolean mySave(FlightTicket flightTicket);
}

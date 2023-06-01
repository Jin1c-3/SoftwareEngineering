package com.yutech.back.mapper.po;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yutech.back.entity.po.FlightTicket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-07
 */
@Mapper
public interface FlightTicketMapper extends BaseMapper<FlightTicket> {

	@Insert("insert into flight_ticket(flight_id,order_id,flight_start_time,flight_end_time,flight_start_port,flight_end_port,ticket_status,passenger_name,seat_type,flight_price) values(#{flightId},#{orderId},#{flightStartTime},#{flightEndTime},#{flightStartPort},#{flightEndPort},#{ticketStatus},#{passengerName},#{seatType},#{flightPrice})")
	boolean mySave(FlightTicket flightTicket);
}

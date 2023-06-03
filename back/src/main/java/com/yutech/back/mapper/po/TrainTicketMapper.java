package com.yutech.back.mapper.po;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yutech.back.entity.po.TrainTicket;
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
public interface TrainTicketMapper extends BaseMapper<TrainTicket> {
    @Insert("insert into train_ticket (train_number_id,order_id,train_ticket_start_time,train_ticket_end_time,train_start_station,train_end_station,ticket_status,passenger_name,seat_type,train_price ) values (#{trainNumberId},#{orderId},#{trainTicketStartTime},#{trainTicketEndTime},#{trainStartStation},#{trainEndStation},#{ticketStatus},#{passengerName},#{seatType},#{trainPrice})")
    public boolean mySave(TrainTicket trainTicket);
}

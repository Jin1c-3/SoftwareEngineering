package com.yutech.back.service.persistence.impl;

import com.yutech.back.entity.po.FlightTime;
import com.yutech.back.mapper.po.FlightTimeMapper;
import com.yutech.back.service.persistence.FlightTimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-03
 */
@Service
public class FlightTimeServiceImpl extends ServiceImpl<FlightTimeMapper, FlightTime> implements FlightTimeService {

}

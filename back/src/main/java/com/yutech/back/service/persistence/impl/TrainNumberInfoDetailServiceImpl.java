package com.yutech.back.service.persistence.impl;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.yutech.back.common.exception.GlobalException;
import com.yutech.back.entity.dto.TrainSeatDTO;
import com.yutech.back.entity.po.TrainNumberInfoDetail;
import com.yutech.back.mapper.po.TrainNumberInfoDetailMapper;
import com.yutech.back.service.persistence.TrainNumberInfoDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-07
 */
@Service
public class TrainNumberInfoDetailServiceImpl extends MppServiceImpl<TrainNumberInfoDetailMapper, TrainNumberInfoDetail> implements TrainNumberInfoDetailService {

	@Autowired
	private TrainNumberInfoDetailMapper trainNumberInfoDetailMapper;

	@Override
	public int queryTrainSeat(TrainSeatDTO trainSeatDTO) {
		Map<String, Object> map = new HashMap<>();
		map.put("seat_type", trainSeatDTO.getSeatType());
		map.put("train_number_ID", trainSeatDTO.getTrainNumberId());
		map.put("day", trainSeatDTO.getDay());
		map.put("start_station", trainSeatDTO.getStartStation());
		map.put("end_station", trainSeatDTO.getEndStation());
		map.put("seat_num", -1);
		try {
			trainNumberInfoDetailMapper.queryTrainSeat(map);
			return (int) map.get("seat_num");
		} catch (Exception e) {
			throw new GlobalException("查询座位失败，存储过程调用失败", e);
		}
	}
}

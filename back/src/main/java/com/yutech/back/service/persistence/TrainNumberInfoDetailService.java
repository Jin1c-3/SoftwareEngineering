package com.yutech.back.service.persistence;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.yutech.back.entity.dto.TrainSeatDTO;
import com.yutech.back.entity.po.TrainNumberInfoDetail;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-07
 */
public interface TrainNumberInfoDetailService extends IMppService<TrainNumberInfoDetail> {

	int queryTrainSeat(TrainSeatDTO trainSeatDTO);
}

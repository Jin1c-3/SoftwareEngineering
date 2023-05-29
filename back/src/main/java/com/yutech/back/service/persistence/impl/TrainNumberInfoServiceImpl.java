package com.yutech.back.service.persistence.impl;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.yutech.back.entity.po.TrainNumberInfo;
import com.yutech.back.mapper.po.TrainNumberInfoMapper;
import com.yutech.back.service.persistence.TrainNumberInfoService;
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
public class TrainNumberInfoServiceImpl extends MppServiceImpl<TrainNumberInfoMapper, TrainNumberInfo> implements TrainNumberInfoService {

}

package com.yutech.back.service.persistence.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yutech.back.entity.po.WholeOrder;
import com.yutech.back.mapper.po.WholeOrderMapper;
import com.yutech.back.service.persistence.WholeOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-03
 */
@Service
public class WholeOrderServiceImpl extends ServiceImpl<WholeOrderMapper, WholeOrder> implements WholeOrderService {

}

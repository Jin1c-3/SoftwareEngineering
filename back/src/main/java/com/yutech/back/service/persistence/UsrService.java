package com.yutech.back.service.persistence;

import com.yutech.back.entity.po.Usr;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-03
 */
public interface UsrService extends IService<Usr> {

	Boolean verifyUnique(Usr usr);
}

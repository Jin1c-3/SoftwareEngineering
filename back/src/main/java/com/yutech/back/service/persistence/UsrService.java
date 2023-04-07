package com.yutech.back.service.persistence;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yutech.back.entity.po.Usr;

public interface UsrService extends IService<Usr> {
	Boolean verifyUnique(Usr usr);
}

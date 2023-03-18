package com.yutech.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yutech.back.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-02-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

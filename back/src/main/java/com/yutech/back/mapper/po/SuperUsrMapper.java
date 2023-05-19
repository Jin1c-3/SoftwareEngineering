package com.yutech.back.mapper.po;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yutech.back.entity.po.SuperUsr;
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
public interface SuperUsrMapper extends BaseMapper<SuperUsr> {

	@Insert("insert into super_usr(super_usr_name, super_usr_pwd) values(#{superUsrName},#{superUsrPwd})")
	Bool mySave(SuperUsr superUsr);
}

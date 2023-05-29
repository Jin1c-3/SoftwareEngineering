package com.yutech.back.mapper.po;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.yutech.back.entity.po.TrainNumberInfoDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-07
 */
@Mapper
public interface TrainNumberInfoDetailMapper extends MppBaseMapper<TrainNumberInfoDetail> {

	@Options(statementType = StatementType.CALLABLE, timeout = 600)
	@Select("{CALL select_num (" +
			"#{seat_type,mode=IN,jdbcType=NVARCHAR}," +
			"#{train_number_ID,mode=IN,jdbcType=NVARCHAR}," +
			"#{day,mode=IN,jdbcType=DATE}," +
			"#{start_station,mode=IN,jdbcType=NVARCHAR}," +
			"#{end_station,mode=IN,jdbcType=NVARCHAR}," +
			"#{seat_num,mode=OUT,jdbcType=INTEGER})}")
	void queryTrainSeat(Map<String, Object> map);
}

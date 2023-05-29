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

	@Options(statementType = StatementType.CALLABLE)
	@Select({"call select_num ",
			"#{map.Seat_Type,mode=IN,jdbcType=NVARCHAR},",
			"#{map.train_number_ID,mode=IN,jdbcType=NVARCHAR},",
			"#{map.day,mode=IN,jdbcType=DATE},",
			"#{map.start_station,mode=IN,jdbcType=NVARCHAR},",
			"#{map.end_station,mode=IN,jdbcType=NVARCHAR},",
			"${map.seat_num,mode=OUT,jdbcType=INTEGER}"
	})
	void queryTrainSeat(Map<String, Object> map);
}

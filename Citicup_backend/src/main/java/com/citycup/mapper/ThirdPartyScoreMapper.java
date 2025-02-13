package com.citycup.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.citycup.entity.ThirdPartyScore;

/**
 * <p>
 * 第三方ESG评级平均分（参考性） Mapper 接口
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Mapper
public interface ThirdPartyScoreMapper extends BaseMapper<ThirdPartyScore> {
	@Select("select * from third_party_score where symbol=#{symbol}")
	public ThirdPartyScore findBySymbol(String symbol);
}

package com.citycup.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.citycup.entity.Fundamentals;

/**
 * <p>
 * 公司基本面数据 Mapper 接口
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Mapper
public interface FundamentalsMapper extends BaseMapper<Fundamentals> {
	@Select("select * from fundamentals where symbol=#{symbol}")
	public Fundamentals findBySymbol(String symbol);
}

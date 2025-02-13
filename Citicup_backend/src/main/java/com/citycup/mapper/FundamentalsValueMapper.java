package com.citycup.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.citycup.entity.FundamentalsValue;

@Mapper
public interface FundamentalsValueMapper extends BaseMapper<FundamentalsValue> {
	@Select("select * from fundamentals_value where symbol=#{symbol}")
	public FundamentalsValue findBySymbol(String symbol);
}

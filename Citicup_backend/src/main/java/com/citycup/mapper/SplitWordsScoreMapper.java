package com.citycup.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.citycup.entity.SplitWordsScore;

/**
 * <p>
 * 词频计算ESG单项分 Mapper 接口
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Mapper
public interface SplitWordsScoreMapper extends BaseMapper<SplitWordsScore> {
	@Select("select * from split_words_score where symbol=#{symbol}")
	public SplitWordsScore findBySymbol(String symbol);
}

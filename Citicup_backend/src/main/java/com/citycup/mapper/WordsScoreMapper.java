package com.citycup.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.citycup.entity.WordsScore;

/**
 * <p>
 * 词频计算总分(议题广泛) Mapper 接口
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Mapper
public interface WordsScoreMapper extends BaseMapper<WordsScore> {
	@Select("select score from words_score where symbol=#{symbol}")
	public Float findBySymbol(String symbol);
	
	@Select("select wordfre from words_score where symbol=#{symbol}")
	public String getWordFrequency(String symbol);
}

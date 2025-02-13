package com.citycup.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.citycup.entity.EmotionScore;

/**
 * <p>
 * 新闻情感分析评分(议题质量) Mapper 接口
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Mapper
public interface EmotionScoreMapper extends BaseMapper<EmotionScore> {
	@Select("select * from emotion_score where symbol=#{symbol}")
	public EmotionScore findBySymbol(String symbol);
}

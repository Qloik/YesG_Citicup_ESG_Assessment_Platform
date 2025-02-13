package com.citycup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.citycup.entity.Keyword;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Mapper
public interface KeywordMapper extends BaseMapper<Keyword> {
	@Select("select industry from keyword")
	public List<String> getAllIndustry();
}

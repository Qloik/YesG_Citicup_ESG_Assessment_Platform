package com.citycup.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.citycup.entity.Keyword;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
public interface KeywordService extends IService<Keyword> {
	public List<String> getAllIndustry();

	public Map<String ,Object> getKeyAndContent(String symbol);
}

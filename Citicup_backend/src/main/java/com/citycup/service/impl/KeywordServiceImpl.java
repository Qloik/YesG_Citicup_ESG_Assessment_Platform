package com.citycup.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.citycup.entity.Keyword;
import com.citycup.entity.Strategy;
import com.citycup.mapper.CompanyMapper;
import com.citycup.mapper.KeywordMapper;
import com.citycup.mapper.StrategyMapper;
import com.citycup.service.KeywordService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Service
public class KeywordServiceImpl extends ServiceImpl<KeywordMapper, Keyword> implements KeywordService {
	@Autowired
	KeywordMapper keywordMapper;
	@Autowired
	CompanyMapper companyMapper;
	@Autowired
	StrategyMapper strategyMapper;
	
	@Override
	public List<String> getAllIndustry() {
		return keywordMapper.getAllIndustry();
	}

	@Override
	public Map<String, Object> getKeyAndContent(String symbol) {
		Map<String,Object> map = new HashMap<>();
		String industry = companyMapper.getESG(symbol).getIndustry();
		Keyword k = keywordMapper.selectOne(new QueryWrapper<Keyword>().eq("industry",industry));
		Strategy strategy = strategyMapper.selectOne(new QueryWrapper<Strategy>().eq("symbol",symbol));
		map.put("keyword",k);
		map.put("strategy",strategy);
		return map;
	}

}

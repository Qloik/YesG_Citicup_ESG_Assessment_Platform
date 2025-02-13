package com.citycup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.citycup.entity.Fundamentals;
import com.citycup.entity.FundamentalsValue;
import com.citycup.mapper.FundamentalsMapper;
import com.citycup.mapper.FundamentalsValueMapper;
import com.citycup.service.FundamentalsService;

/**
 * <p>
 * 公司基本面数据 服务实现类
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Service
public class FundamentalsServiceImpl extends ServiceImpl<FundamentalsMapper, Fundamentals> implements FundamentalsService {
    @Autowired
    FundamentalsMapper fundamentalsMapper;
    @Autowired
    FundamentalsValueMapper mapper;
    
    @Override
    public FundamentalsValue getFundamentalsValue(String symbol) {
        return mapper.findBySymbol(symbol);
    }

	@Override
	public Fundamentals getFundamentalsBySymbol(String symbol) {
		return fundamentalsMapper.findBySymbol(symbol);
	}
}

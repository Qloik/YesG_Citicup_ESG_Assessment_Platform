package com.citycup.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.citycup.entity.Fundamentals;
import com.citycup.mapper.FundamentalsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.citycup.entity.FundamentalsRank;
import com.citycup.mapper.FundamentalsRankMapper;
import com.citycup.service.FundamentalsRankService;

/**
 * <p>
 * 公司基本面数据在同行业排名 服务实现类
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Service
public class FundamentalsRankServiceImpl extends ServiceImpl<FundamentalsRankMapper, FundamentalsRank> implements FundamentalsRankService {
    @Autowired
    FundamentalsRankMapper fundamentalsRankMapper;
    @Autowired
    FundamentalsMapper fMapper;
    @Override
    public Map<String, Object> getFundamentalsRankBySymbol(String symbol) {
        QueryWrapper<FundamentalsRank> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("symbol", symbol);
        Map<String, Object> response = new HashMap<>();
        response.put("data", fundamentalsRankMapper.selectOne(queryWrapper));
        return response;
    }

    @Override
    public Map<String, Object> getFundamentalsRankInfoBySymbol(String symbol) {
        QueryWrapper<FundamentalsRank> q1 = new QueryWrapper<>();
        QueryWrapper<Fundamentals> q2 = new QueryWrapper<>();
        q1.eq("symbol",symbol);
        q2.eq("symbol",symbol);
        Map<String,Object> response = new HashMap<>();
        response.put("fundamentalsRank",fundamentalsRankMapper.selectOne(q1));
        response.put("fundamentals",fMapper.selectOne(q2));
        return response;
    }
}

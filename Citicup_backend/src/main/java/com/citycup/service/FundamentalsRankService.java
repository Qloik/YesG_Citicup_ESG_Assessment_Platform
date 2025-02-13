package com.citycup.service;

import com.citycup.entity.FundamentalsRank;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 公司基本面数据在同行业排名 服务类
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
public interface FundamentalsRankService extends IService<FundamentalsRank> {
    // 根据公司代码获取公司基本面数据在同行业排名
    public Map<String, Object> getFundamentalsRankBySymbol(String symbol);

    public Map<String,Object> getFundamentalsRankInfoBySymbol(String symbol);
}

package com.citycup.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.citycup.entity.Fundamentals;
import com.citycup.entity.FundamentalsValue;

/**
 * <p>
 * 公司基本面数据 服务类
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
public interface FundamentalsService extends IService<Fundamentals> {
    public Fundamentals getFundamentalsBySymbol(String symbol);
    
    public FundamentalsValue getFundamentalsValue(String symbol);
}

package com.citycup.controller;


import com.citycup.service.FundamentalsRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 公司基本面数据在同行业排名 前端控制器
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@RestController
public class FundamentalsRankController {
    @Autowired
    FundamentalsRankService fundamentalsRankService;

    @GetMapping("/fundamentals_rank/{symbol}")
    public Map<String, Object> getFundamentalsRank(@PathVariable String symbol) {
        return fundamentalsRankService.getFundamentalsRankBySymbol(symbol);
    }

    @GetMapping("/fundamentals_rank/info/{symbol}")
    public Map<String,Object> getFundamentalsRankInfo(@PathVariable String symbol)
    {
        return fundamentalsRankService.getFundamentalsRankInfoBySymbol(symbol);
    }

}

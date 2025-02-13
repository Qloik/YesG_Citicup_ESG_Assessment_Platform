package com.citycup.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citycup.entity.Fundamentals;
import com.citycup.entity.FundamentalsValue;
import com.citycup.service.FundamentalsService;

/**
 * <p>
 * 公司基本面数据 前端控制器
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@RestController
@RequestMapping("/fundamentals")
public class FundamentalsController {
    @Autowired
    FundamentalsService fundamentalsService;
    
    @PostMapping("/getFundamentals")
    public Fundamentals getFundamentals(String symbol) {
        return fundamentalsService.getFundamentalsBySymbol(symbol);
    }
    
    @PostMapping("/getBySymbol")
    public FundamentalsValue getFundamentalsBySymbol(String symbol)
    {
        return fundamentalsService.getFundamentalsValue(symbol);
    }
}

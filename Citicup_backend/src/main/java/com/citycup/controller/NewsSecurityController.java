package com.citycup.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.citycup.entity.NewsSecurity;
import com.citycup.service.NewsSecurityService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@RestController
@RequestMapping("/news-security")
public class NewsSecurityController {
    @Autowired
    NewsSecurityService newsSecurityService;
    @GetMapping("/getAll")
    public IPage<NewsSecurity> getAllNews(long current,long size)
    {
        return newsSecurityService.getAllNews(current,size);
    }
}

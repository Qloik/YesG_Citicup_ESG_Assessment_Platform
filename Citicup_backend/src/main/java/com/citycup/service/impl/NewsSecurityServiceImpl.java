package com.citycup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.citycup.entity.NewsSecurity;
import com.citycup.mapper.NewsSecurityMapper;
import com.citycup.service.NewsSecurityService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Service
public class NewsSecurityServiceImpl extends ServiceImpl<NewsSecurityMapper, NewsSecurity> implements NewsSecurityService {

    @Autowired
    private NewsSecurityMapper newsSecurityMapper;
    @Override
    public IPage<NewsSecurity> getAllNews(long current,long size) {
        IPage<NewsSecurity> iPage = new Page<>(current,size);
        iPage = newsSecurityMapper.selectPage(iPage,null);
        return iPage;
    }
}

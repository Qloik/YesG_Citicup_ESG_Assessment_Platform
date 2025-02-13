package com.citycup.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.citycup.entity.NewsSecurity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
public interface NewsSecurityService extends IService<NewsSecurity> {
    IPage<NewsSecurity> getAllNews(long current,long size);
}

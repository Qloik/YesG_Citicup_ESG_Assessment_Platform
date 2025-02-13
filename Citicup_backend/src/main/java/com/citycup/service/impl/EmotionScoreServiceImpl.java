package com.citycup.service.impl;

import com.citycup.entity.EmotionScore;
import com.citycup.mapper.EmotionScoreMapper;
import com.citycup.service.EmotionScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 新闻情感分析评分(议题质量) 服务实现类
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Service
public class EmotionScoreServiceImpl extends ServiceImpl<EmotionScoreMapper, EmotionScore> implements EmotionScoreService {

}

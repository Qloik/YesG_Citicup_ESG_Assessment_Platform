package com.citycup.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.citycup.entity.WordsScore;

/**
 * <p>
 * 词频计算总分(议题广泛) 服务类
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
public interface WordsScoreService extends IService<WordsScore> {
	public String getWordFrequency(String symbol);
}

package com.citycup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.citycup.entity.WordsScore;
import com.citycup.mapper.WordsScoreMapper;
import com.citycup.service.WordsScoreService;

/**
 * <p>
 * 词频计算总分(议题广泛) 服务实现类
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Service
public class WordsScoreServiceImpl extends ServiceImpl<WordsScoreMapper, WordsScore> implements WordsScoreService {
	@Autowired
	private WordsScoreMapper wordsScoreMapper;
	
	@Override
	public String getWordFrequency(String symbol) {
		return wordsScoreMapper.getWordFrequency(symbol);
	}

}

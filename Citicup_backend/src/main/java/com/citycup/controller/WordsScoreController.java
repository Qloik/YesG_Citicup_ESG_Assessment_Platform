package com.citycup.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citycup.service.WordsScoreService;

/**
 * <p>
 * 词频计算总分(议题广泛) 前端控制器
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@RestController
@RequestMapping("/words-score")
public class WordsScoreController {
	@Autowired
	private WordsScoreService wordsScoreService;
	
	@PostMapping("/getWordFre")
	public List<List<String>> getWordFre(String symbol){
		String raw=wordsScoreService.getWordFrequency(symbol);
		if(raw==null) {
			return null;
		}
		String[] splitStrings=raw.split(" ");
		List<String> word=new ArrayList<>();
		List<String> frequency=new ArrayList<>();
		int n=splitStrings.length;
		for(int i=0;i<n;i=i+2) {
			word.add(splitStrings[i]);
			frequency.add(splitStrings[i+1]);
		}
		List<List<String>> resultList=new ArrayList<>();
		resultList.add(word);
		resultList.add(frequency);
		return resultList;
	}
}

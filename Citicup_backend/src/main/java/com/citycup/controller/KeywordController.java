package com.citycup.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citycup.service.KeywordService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@RestController
@RequestMapping("/keyword")
public class KeywordController {
	@Autowired
	KeywordService keywordService;
	
	@GetMapping("/getAllIndustry")
	public List<String> getAllIndustry(){
		return keywordService.getAllIndustry();
	}

	@PostMapping("/getKeyAndContent")
	public Map<String,Object> getKeyAndContent(String symbol)
	{
		return keywordService.getKeyAndContent(symbol);
	}
}

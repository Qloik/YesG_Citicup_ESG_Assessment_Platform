package com.citycup.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citycup.entity.Company;
import com.citycup.service.CompanyService;

/**
 * <p>
 * 所有港股公司 前端控制器
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	CompanyService companyService;
	
	@GetMapping("/getAllScore")
	public List<Company> getAllScore(){
		return companyService.getCompanies();
	}
	
	//根据股份代号，获取公司的部分评分
	@PostMapping("/getEsg")
	public Map<String, Float> getEsg(String symbol){
		return companyService.getEsgBySymbol(symbol);
	}
	
	@PostMapping("/getDetailedInfo")
	public Map<String, String> getDetailedInfo(String symbol){
		return companyService.getDetailedInfo(symbol);
	}
	
	@PostMapping("/getTopByIndustry")
	public List<Company> getTopByIndustry(String industry){
		return companyService.getTopByIndustry(industry);
	}

	@PostMapping("/getEsgBySymbol")
	public Float getEsgBySymbol(String symbol)
	{
		return companyService.getSeg(symbol);
	}
}

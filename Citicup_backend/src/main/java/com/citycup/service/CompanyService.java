package com.citycup.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.citycup.entity.Company;

/**
 * <p>
 * 所有港股公司 服务类
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
public interface CompanyService extends IService<Company> {
	public List<Company> getCompanies();
	
	public Map<String, Float> getEsgBySymbol(String symbol);
	
	public Map<String, String> getDetailedInfo(String symbol);
	
	public List<Company> getTopByIndustry(String industry);

	public Float getSeg(String symbol);
}

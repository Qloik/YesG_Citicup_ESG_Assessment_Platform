package com.citycup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.citycup.entity.Company;

/**
 * <p>
 * 所有港股公司 Mapper 接口
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Mapper
public interface CompanyMapper extends BaseMapper<Company> {
	@Select("select company.symbol,company.name,company.esg,industry,score as words,third_party_score.esg as third,emotion_score as emotion "
			+ "from company left join words_score on company.symbol=words_score.symbol "
			+ "left join third_party_score on company.symbol=third_party_score.symbol "
			+ "left join emotion_score on emotion_score.symbol=company.symbol")
	public List<Company> getCompanies();
	
	@Select("select * from company where company.symbol=#{symbol}")
	public Company getESG(String symbol);
	
	@Select("select * from company where industry=#{industry} and company.rank<0.06 order by esg desc")
	public List<Company> getTopByIndustry(String industry);
}

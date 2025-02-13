package com.citycup.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.citycup.entity.Company;
import com.citycup.entity.EmotionScore;
import com.citycup.entity.SplitWordsScore;
import com.citycup.entity.ThirdPartyScore;
import com.citycup.mapper.CompanyMapper;
import com.citycup.mapper.EmotionScoreMapper;
import com.citycup.mapper.SplitWordsScoreMapper;
import com.citycup.mapper.ThirdPartyScoreMapper;
import com.citycup.service.CompanyService;

/**
 * <p>
 * 所有港股公司 服务实现类
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
	@Autowired
	CompanyMapper companyMapper;
	@Autowired
	EmotionScoreMapper emotionScoreMapper;
	@Autowired
	SplitWordsScoreMapper scoreMapper;
	@Autowired
	ThirdPartyScoreMapper thirdMapper;
	
	@Override
	public List<Company> getCompanies() {
		return companyMapper.getCompanies();
	}

	@Override
	public Map<String, Float> getEsgBySymbol(String symbol) {
		Map<String, Float> map=new HashMap<>();
		EmotionScore emotionScore = emotionScoreMapper.findBySymbol(symbol);
		if(emotionScore!=null) {
			map.put("esg", companyMapper.getESG(symbol).getEsg());
			map.put("e_score", emotionScore.getEScore());
			map.put("s_score",emotionScore.getSScore());
			map.put("g_score",emotionScore.getGScore());
		}
		SplitWordsScore score=scoreMapper.findBySymbol(symbol);
		if(score!=null) {
			map.put("soc", score.getSoc());
			map.put("gov", score.getGov());
			map.put("env", score.getEnv());
		}
		return map;
	}

	@Override
	public Map<String, String> getDetailedInfo(String symbol) {
		Map<String, String> map=new HashMap<>();
		Company company=companyMapper.getESG(symbol);
		if(company==null) {
			map.put("result", "symbol not exist");
			return map;
		}
		map.put("result", "symbol found");
		map.put("symbol", symbol);
		map.put("name", company.getName());
		map.put("industry", company.getIndustry());
		if(company.getRank()!=null) {
			map.put("esg", company.getEsg().toString());
			float rank=company.getRank()*100f;
			Float Rank=Math.round(rank * 100) / 100f;
			map.put("esgRank", Rank.toString());
		}
		else {
			map.put("esg", "");
			map.put("esgRank", "");
		}
		// 情感性 小分
		EmotionScore emotion=emotionScoreMapper.findBySymbol(symbol);
		map.put("emotion_score", emotion.getEmotionScore().toString());
		map.put("EEmotion", emotion.getEScore().toString());
		map.put("SEmotion", emotion.getSScore().toString());
		map.put("GEmotion", emotion.getGScore().toString());
		if(emotion.getRank()!=null) {
			float rank=emotion.getRank()*100f;
			Float Rank=Math.round(rank * 100) / 100f;
			map.put("emotionRank", Rank.toString());
		}
		// 广泛性 小分
		SplitWordsScore score=scoreMapper.findBySymbol(symbol);
		if(score!=null) {
			map.put("SWords", score.getSoc().toString());
			map.put("GWords", score.getGov().toString());
			map.put("EWords", score.getEnv().toString());
			if(score.getE1()!=null) {
				map.put("e1", score.getE1().toString());
				map.put("e2", score.getE2().toString());
				map.put("e3", score.getE3().toString());
				map.put("e4", score.getE4().toString());
				map.put("s1", score.getS1().toString());
				map.put("s2", score.getS2().toString());
				map.put("s3", score.getS3().toString());
				map.put("s4", score.getS4().toString());
				map.put("g1", score.getG1().toString());
				map.put("g2", score.getG2().toString());
				map.put("g3", score.getG3().toString());
			}
		}
		else {
			map.put("SWords", "");
			map.put("GWords", "");
			map.put("EWords", "");
		}
		if(score==null||score.getE1()==null) {
			map.put("e1", "");
			map.put("e2", "");
			map.put("e3", "");
			map.put("e4", "");
			map.put("s1", "");
			map.put("s2", "");
			map.put("s3", "");
			map.put("s4", "");
			map.put("g1", "");
			map.put("g2", "");
			map.put("g3", "");
		}
		// 第三方 小分
		ThirdPartyScore third=thirdMapper.findBySymbol(symbol);
		if(third.getRank()!=null) {
			map.put("thirdParty", third.getEsg().toString());
			float rank=third.getRank()*100f;
			Float Rank=Math.round(rank * 100) / 100f;
			map.put("thirdPartyRank", Rank.toString());
		}
		else {
			map.put("thirdParty", "");
			map.put("thirdPartyRank", "");
		}
		return map;
	}

	@Override
	public List<Company> getTopByIndustry(String industry) {
		return companyMapper.getTopByIndustry(industry);
	}

	@Override
	public Float getSeg(String symbol) {
		Company company = companyMapper.getESG(symbol);
		return company.getEsg();
	}

}

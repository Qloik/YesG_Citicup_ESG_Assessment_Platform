package com.citycup.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 新闻情感分析评分(议题质量)
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="EmotionScore对象", description="新闻情感分析评分(议题质量)")
public class EmotionScore implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公司代码")
    private String symbol;

    private Float emotionScore;
    
    private Float eScore;
    
    private Float sScore;
    
    private Float gScore;
    
    private Float rank;
}

package com.citycup.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 词频计算ESG单项分
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SplitWordsScore对象", description="词频计算ESG单项分")
public class SplitWordsScore implements Serializable {

    private static final long serialVersionUID = 1L;

    private String symbol;

    private Float env;
    
    private Float e1;
    private Float e2;
    private Float e3;
    private Float e4;

    private Float soc;
    
    private Float s1;
    private Float s2;
    private Float s3;
    private Float s4;

    private Float gov;
    
    private Float g1;
    private Float g2;
    private Float g3;
}

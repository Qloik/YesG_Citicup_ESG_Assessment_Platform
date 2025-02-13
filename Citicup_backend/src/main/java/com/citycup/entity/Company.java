package com.citycup.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 所有港股公司
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Company对象", description="所有港股公司")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "股票代码")
    private String symbol;

    @ApiModelProperty(value = "简体公司名称")
    private String name;

    @ApiModelProperty(value = "繁体公司名称")
    @TableField("nameF")
    private String namef;

    @ApiModelProperty(value = "英文公司名称")
    private String engname;

    @ApiModelProperty(value = "行业分类")
    private String industry;

    @ApiModelProperty(value = "总评分")
    private Float esg;
    
    private Float rank;
    
    @TableField(exist = false)
    private Float third;
    @TableField(exist = false)
    private Float words;
    @TableField(exist = false)
    private Float emotion;

}

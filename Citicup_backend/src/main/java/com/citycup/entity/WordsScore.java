package com.citycup.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 词频计算总分(议题广泛)
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="WordsScore对象", description="词频计算总分(议题广泛)")
public class WordsScore implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "股份代号")
    private String symbol;

    @ApiModelProperty(value = "繁体公司名称")
    private String namef;

    @ApiModelProperty(value = "简体公司名称")
    private String name;

    @ApiModelProperty(value = "议题广泛性得分")
    private Float score;

    @ApiModelProperty(value = "关键词及词频")
    private String wordfre;


}

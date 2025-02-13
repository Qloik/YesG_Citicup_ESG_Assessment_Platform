package com.citycup.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 公司基本面数据在同行业排名
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="FundamentalsRank对象", description="公司基本面数据在同行业排名")
public class FundamentalsRank implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "股票代码")
    private String symbol;

    @ApiModelProperty(value = "股东权益")
    private String equity;

    @ApiModelProperty(value = "净利润")
    @TableField("netProfit")
    private String netprofit;

    @ApiModelProperty(value = "总营收入")
    private String income;

    @ApiModelProperty(value = "市盈率")
    private String ying;

    @ApiModelProperty(value = "市净率")
    private String jing;

    @ApiModelProperty(value = "市销率")
    private String xiao;

    @ApiModelProperty(value = "市现率")
    private String xian;

    @ApiModelProperty(value = "净资产收益率")
    private String retu;

    @ApiModelProperty(value = "股息率")
    private String yield;


}

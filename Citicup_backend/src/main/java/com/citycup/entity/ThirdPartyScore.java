package com.citycup.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 第三方ESG评级平均分（参考性）
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ThirdPartyScore对象", description="第三方ESG评级平均分（参考性）")
public class ThirdPartyScore implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公司股票代码")
    private String symbol;

    @ApiModelProperty(value = "第三方平均值")
    private Float esg;

    private Float rank;
}

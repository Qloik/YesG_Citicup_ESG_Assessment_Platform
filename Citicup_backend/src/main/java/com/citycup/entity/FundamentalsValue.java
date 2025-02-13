package com.citycup.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="FundamentalsVale对象", description="包含公司股价")
@TableName("fundamentals_value")
public class FundamentalsValue {
    String symbol;
    String name;
    @TableField("stockPrice")
    String stockPrice;
}

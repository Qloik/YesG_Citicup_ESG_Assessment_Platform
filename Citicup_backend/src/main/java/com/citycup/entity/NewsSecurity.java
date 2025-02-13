package com.citycup.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Dql
 * @since 2023-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="NewsSecurity对象", description="")
public class NewsSecurity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("NewsID")
    private String newsid;

    @TableField("DeclareDate")
    private String declaredate;

    @TableField("Title")
    private String title;

    @TableField("Symbol")
    private String symbol;

    private String emotion;

    private String esg;


}

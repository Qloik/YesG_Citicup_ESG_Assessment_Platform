package com.citycup.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Dql
 * @since 2023-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "电话")
    private String id;

    private String name;

    private String password;

    @ApiModelProperty(value = "登录时间")
    @TableField("loginTime")
    private LocalDateTime logintime;

    @ApiModelProperty(value = "使用时长")
    @TableField("useTime")
    private Integer usetime;


}

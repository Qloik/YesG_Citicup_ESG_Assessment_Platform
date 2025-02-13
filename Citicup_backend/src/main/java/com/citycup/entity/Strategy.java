package com.citycup.entity;

import java.io.Serializable;

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
@ApiModel(value="Strategy对象", description="")
public class Strategy implements Serializable {

    private static final long serialVersionUID = 1L;

    private String symbol;

    private String text1;

    private String text2;

    private String text3;


}

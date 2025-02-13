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
@ApiModel(value="Keyword对象", description="")
public class Keyword implements Serializable {

    private static final long serialVersionUID = 1L;

    private String industry;

    private String keyword1;

    private String keyword2;

    private String keyword3;


}

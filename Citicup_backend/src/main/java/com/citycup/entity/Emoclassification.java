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
@ApiModel(value="Emoclassification对象", description="")
public class Emoclassification implements Serializable {

    private static final long serialVersionUID = 1L;

    private String day;

    private String name;

    private String symbol;

    private String emotion;

    private String esg;

    private String text;


}

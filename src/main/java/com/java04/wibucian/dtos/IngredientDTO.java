package com.java04.wibucian.dtos;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class IngredientDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String idIngredient;

    private String ingredientName;

    private Date expiryIngredient;

    private Float price;

    private Double quantity;

    private String origin;

    private String unit;

}

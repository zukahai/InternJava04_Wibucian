package com.java04.wibucian.vos;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class IngredientQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idIngredient;

    private String ingredientName;

    private Date expiryIngredient;

    private Float price;

    private String origin;

    private String unit;

}

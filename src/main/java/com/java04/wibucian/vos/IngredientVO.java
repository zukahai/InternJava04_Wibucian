package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class IngredientVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "idIngredient can not null")
    private String idIngredient;

    @NotNull(message = "ingredientName can not null")
    private String ingredientName;

    private Date expiryIngredient;

    @NotNull(message = "price can not null")
    private Float price;

    @NotNull(message = "origin can not null")
    private String origin;

    @NotNull(message = "unit can not null")
    private String unit;

}

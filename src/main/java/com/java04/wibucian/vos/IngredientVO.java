package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Data
public class IngredientVO implements Serializable {
    private static final long serialVersionUID = 1L;



    @NotNull(message = "ingredientName can not null")
    private String ingredientName;

    private String expiryIngredient;

    @NotNull(message = "price can not null")
    private Double price;

    @NotNull(message = "origin can not null")
    private String origin;

    @NotNull(message = "unit can not null")
    private String unit;
    public Date getExpiryIngredient() {
        System.out.println(this.expiryIngredient);
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(this.expiryIngredient);
        } catch(ParseException e) {
            throw new RuntimeException("Ngày không hợp lệ");
        }
    }
}

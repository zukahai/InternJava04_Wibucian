package com.java04.wibucian.vos;

import javax.validation.constraints.NotNull;
import java.util.Date;


public class DetailIngredientVO {
    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "idDetailIngredient can not null")
    private String idDetailIngredient;

    private String idIngredient;

    private Float valueChange;

    @NotNull(message = "content can not null")
    private String content;

    private Date dateTime;

}

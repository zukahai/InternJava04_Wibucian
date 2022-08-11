package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class DetailProductVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "idDetailProduct can not null")
    private String idDetailProduct;

    private String idProduct;

    private String idIngredient;

    private Float quantity;

}

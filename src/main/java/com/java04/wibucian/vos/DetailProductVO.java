package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class DetailProductVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idProduct;

    private String idIngredient;

    private Float quantity;

}

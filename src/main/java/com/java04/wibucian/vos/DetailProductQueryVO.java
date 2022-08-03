package com.java04.wibucian.vos;


import lombok.Data;

import java.io.Serializable;

@Data
public class DetailProductQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idDetailProduct;

    private String idProduct;

    private String idIngredient;

    private Integer quantity;

}

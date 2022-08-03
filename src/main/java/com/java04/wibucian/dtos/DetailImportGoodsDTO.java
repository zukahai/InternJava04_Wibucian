package com.java04.wibucian.dtos;


import lombok.Data;

import java.io.Serializable;

@Data
public class DetailImportGoodsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String idDetailImportGoods;

    private String idIngredient;

    private String idImportGoods;

    private Integer quantity;

}

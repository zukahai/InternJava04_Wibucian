package com.java04.wibucian.vos;


import lombok.Data;

import java.io.Serializable;

@Data
public class DetailImportGoodsQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idDetailImportGoods;

    private String idIngredient;

    private String idImportGoods;

    private Integer quantity;

}

package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class DetailImportGoodsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idDetailImportGoods;

    private String idIngredient;

    private String idImportGoods;

    private float quantity;

}

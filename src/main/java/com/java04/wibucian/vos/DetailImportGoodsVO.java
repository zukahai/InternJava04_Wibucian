package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class DetailImportGoodsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "idDetailImportGoods can not null")
    private String idDetailImportGoods;

    private String idIngredient;

    private String idImportGoods;

    private Integer quantity;

}

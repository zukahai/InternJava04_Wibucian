package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class ProductVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "idProduct can not null")
    private String idProduct;

    private String idSale;

    private String idProductType;

    private String describe;

    @NotNull(message = "srcImage can not null")
    private String srcImage;

    @NotNull(message = "productName can not null")
    private String productName;

}

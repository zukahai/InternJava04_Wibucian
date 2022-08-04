package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class ProductVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Product type can not null")
    private String idProductType;

    @NotNull(message = "Describe can not null")
    private String describe;

    @NotNull(message = "srcImage can not null")
    private String srcImage;

    @NotNull(message = "productName can not null")
    private String productName;

}

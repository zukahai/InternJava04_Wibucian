package com.java04.wibucian.vos;


import lombok.Data;

import java.io.Serializable;

@Data
public class ProductQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idProduct;

    private String idSale;

    private String idProductType;

    private String describe;

    private String srcImage;

    private String productName;

}

package com.java04.wibucian.dtos;


import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String idProduct;

    private String idSale;

    private String idProductType;

    private String describe;

    private String srcImage;

    private String productName;

}

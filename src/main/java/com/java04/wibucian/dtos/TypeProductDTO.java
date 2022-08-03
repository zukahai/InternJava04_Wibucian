package com.java04.wibucian.dtos;


import lombok.Data;

import java.io.Serializable;

@Data
public class TypeProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String idTypeProduct;

    private String productName;

}

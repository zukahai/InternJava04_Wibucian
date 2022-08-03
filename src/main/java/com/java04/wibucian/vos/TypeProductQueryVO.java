package com.java04.wibucian.vos;


import lombok.Data;

import java.io.Serializable;

@Data
public class TypeProductQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idTypeProduct;

    private String productName;

}

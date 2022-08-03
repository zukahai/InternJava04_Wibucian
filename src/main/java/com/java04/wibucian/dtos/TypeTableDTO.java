package com.java04.wibucian.dtos;


import lombok.Data;

import java.io.Serializable;

@Data
public class TypeTableDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String idTypeTable;

    private String typeName;

    private Float price;

}

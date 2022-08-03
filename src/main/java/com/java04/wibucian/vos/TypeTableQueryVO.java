package com.java04.wibucian.vos;


import lombok.Data;

import java.io.Serializable;

@Data
public class TypeTableQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idTypeTable;

    private String typeName;

    private Float price;

}

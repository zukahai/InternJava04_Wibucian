package com.java04.wibucian.vos;


import lombok.Data;

import java.io.Serializable;

@Data
public class TablecfQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idTablecf;

    private String idTypeTable;

    private String describe;

    private Integer capacity;

}

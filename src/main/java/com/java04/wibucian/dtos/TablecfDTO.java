package com.java04.wibucian.dtos;


import lombok.Data;

import java.io.Serializable;

@Data
public class TablecfDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String idTablecf;

    private String idTypeTable;

    private String describe;

    private Integer capacity;

}

package com.java04.wibucian.dtos;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DetailGroupTableDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String idDetailGroupTable;

    private String idGroupTable;

    private String idTablecf;

    private Date groupTime;

}

package com.java04.wibucian.vos;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DetailGroupTableQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idDetailGroupTable;

    private String idGroupTable;

    private String idTablecf;

    private Date groupTime;

}

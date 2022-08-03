package com.java04.wibucian.vos;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ImportGoodsQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idImportGoods;

    private String idEmployee;

    private Date timeImport;

}

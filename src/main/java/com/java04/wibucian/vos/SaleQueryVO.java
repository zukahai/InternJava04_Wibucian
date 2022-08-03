package com.java04.wibucian.vos;


import lombok.Data;

import java.io.Serializable;

@Data
public class SaleQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idSale;

    private String pcent;

    private Float price;

}

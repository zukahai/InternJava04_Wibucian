package com.java04.wibucian.dtos;


import lombok.Data;

import java.io.Serializable;

@Data
public class SaleDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String idSale;

    private String pcent;

    private Float price;

}

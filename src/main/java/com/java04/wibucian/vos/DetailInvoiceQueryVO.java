package com.java04.wibucian.vos;


import lombok.Data;

import java.io.Serializable;

@Data
public class DetailInvoiceQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idDetailInvoice;

    private String idInvoice;

    private String idProduct;

    private Integer quantity;

}

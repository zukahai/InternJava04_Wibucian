package com.java04.wibucian.vos;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class InvoiceNoMapPingQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idInvoice;

    private String idEmployee;

    private String idGroupTable;

    private String customerName;

    private Date dateTime;

    private Float totalMoney;

}

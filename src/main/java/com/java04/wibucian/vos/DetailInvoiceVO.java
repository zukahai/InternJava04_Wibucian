package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class DetailInvoiceVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "idDetailInvoice can not null")
    private String idDetailInvoice;

    private String idInvoice;

    private String idProduct;

    private Integer quantity;

    private Date dateTime;

    private Float totalMoney;

}

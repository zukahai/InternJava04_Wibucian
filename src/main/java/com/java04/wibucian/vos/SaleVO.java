package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class SaleVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "idSale can not null")
    private String idSale;

    @NotNull(message = "pcent can not null")
    private String pcent;

    private Date timeStart;

    private Date timeEnd;

}

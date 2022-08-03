package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class DetailGroupTableVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "idDetailGroupTable can not null")
    private String idDetailGroupTable;

    private String idGroupTable;

    private String idTablecf;

    private Date groupTime;

}

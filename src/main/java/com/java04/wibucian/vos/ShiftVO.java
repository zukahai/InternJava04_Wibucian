package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class ShiftVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "idShift can not null")
    private String idShift;

    private String idEmployee;

    private String idEmployeeChange;

    private Date timeStart;

    private Date timeEnd;

}

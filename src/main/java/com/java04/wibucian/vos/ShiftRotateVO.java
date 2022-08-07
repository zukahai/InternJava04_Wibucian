package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class ShiftRotateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "idShiftRotate can not null")
    private String idShiftRotate;

    private String idShift;

    private String idEmployee;

    private String idEmployeeChange;

}

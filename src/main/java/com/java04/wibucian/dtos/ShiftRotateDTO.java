package com.java04.wibucian.dtos;


import lombok.Data;

import java.io.Serializable;

@Data
public class ShiftRotateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String idShiftRotate;

    private String idShift;

    private String idEmployee;

    private String idEmployeeChange;

}

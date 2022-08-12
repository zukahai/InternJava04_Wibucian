package com.java04.wibucian.dtos;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ShiftDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String idShift;

    private String idEmployee;

    private String idEmployeeChange;

    private Date timeStart;

    private Date timeEnd;

}

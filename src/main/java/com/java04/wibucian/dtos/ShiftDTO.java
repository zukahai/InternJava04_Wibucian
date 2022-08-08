package com.java04.wibucian.dtos;


import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
public class ShiftDTO implements Serializable {
    private String id;

    private String idEmployee;

    private String idEmployeeChange;

    private boolean overtimeRequest;

    private String start;

    private String end;

}

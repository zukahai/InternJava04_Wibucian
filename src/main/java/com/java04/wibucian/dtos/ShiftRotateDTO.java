package com.java04.wibucian.dtos;


import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
public class ShiftRotateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String employeeId;

    private String employeeName;

    private String shiftId;

    private Date shiftDate;

    private int shiftCode;

    private String employeeChangeId;

    private String employeeChangeName;

    private String shiftExchangeId;

    private Date shiftExchangeDate;

    private int shiftExchangeCode;

    private int status;

    private Instant createTime;

    private Instant acceptTime;

    private Instant approveTime;

    private Instant rejectTime;
}

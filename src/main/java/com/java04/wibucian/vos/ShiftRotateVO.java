package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class ShiftRotateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idEmployee;

    @NotNull(message = "Mã ca làm việc không được trống")
    private String idShift;

    @NotNull(message = "Mã ca làm việc trao đổi không được trống")
    private String idShiftExchange;
}

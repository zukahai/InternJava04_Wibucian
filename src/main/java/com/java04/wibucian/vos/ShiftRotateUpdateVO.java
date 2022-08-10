package com.java04.wibucian.vos;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class ShiftRotateUpdateVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idEmployee;

    @NotNull(message = "Mã yêu cầu xoay ca là bắt buộc")
    private String shiftRotateId;

    @NotNull(message = "Hành động là bắt buộc")
    private boolean approve;

}

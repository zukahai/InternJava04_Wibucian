package com.java04.wibucian.vos;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class ShiftUpdateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Vui lòng nhập vào mã nhân viên")
    private String idEmployee;

}

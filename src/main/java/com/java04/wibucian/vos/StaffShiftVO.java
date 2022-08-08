package com.java04.wibucian.vos;

import com.java04.wibucian.validations.annotation.ValidShiftCode;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Data
public class StaffShiftVO extends ShiftVO implements Serializable {
    private static final long serialVersionUID = 1L;

    //    @NotNull(message = "Vui lòng nhập vào mã nhân viên")
    //    @Pattern(regexp = "", message = "Mã nhân viên không hợp lệ")
    private String idEmployee;
}

package com.java04.wibucian.vos;

import com.java04.wibucian.validations.annotation.ValidShiftCode;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Data
public class WorkPlanVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Vui lòng nhập có làm việc hay không")
    private Boolean work;
}

package com.java04.wibucian.vos;


import com.java04.wibucian.commons.Constant;
import com.java04.wibucian.commons.Utils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class StaffEmployeeUpdateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "name can not null")
    private String name;

    @NotNull(message = "address can not null")
    private String address;

    @NotNull(message = "phoneNumber can not null")
    private String phoneNumber;

    @NotNull(message = "maritalStatus can not null")
    private String maritalStatus;

    @NotNull(message = "Birthday can not be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String birthDay;

    public Date getBirthday() {
        return Utils.getCalendarInstanceFromFormat(this.birthDay,
                                                   Constant.YYYY_MM_DD_FORMAT)
                    .getTime();
    }
}

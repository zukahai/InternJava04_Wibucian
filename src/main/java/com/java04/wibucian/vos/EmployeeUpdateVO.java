package com.java04.wibucian.vos;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class EmployeeUpdateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "name can not null")
    private String name;

    @NotNull(message = "address can not null")
    private String address;

    @NotNull(message = "phoneNumber can not null")
    private String phoneNumber;

//    @NotNull(message = "maritalStatus can not null")
//    private String maritalStatus;

//    private String birthDay;
}

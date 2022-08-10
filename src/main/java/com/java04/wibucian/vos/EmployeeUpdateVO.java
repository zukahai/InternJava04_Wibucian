package com.java04.wibucian.vos;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class EmployeeUpdateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "name can not null")
    private String name;

    @NotNull(message = "address can not null")
    private String address;

    @NotNull(message = "email can not null")
    private String email;

    @NotNull(message = "phoneNumber can not null")
    private String phoneNumber;

    @NotNull(message = "maritalStatus can not null")
    private String maritalStatus;
}

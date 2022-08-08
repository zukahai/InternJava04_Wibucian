package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;


@Data
public class EmployeeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "name can not null")
    private String name;

    @NotNull(message = "address can not null")
    private String address;

    @NotNull(message = "srcEmployee can not null")
    private String srcEmployee;

    @NotNull(message = "email can not null")
    private String email;

    @NotNull(message = "phoneNumber can not null")
    private String phoneNumber;

    @NotNull(message = "gender can not null")
    private String gender;

    private Instant birthDay;

    @NotNull(message = "maritalStatus can not null")
    private String maritalStatus;

    private Double coefficientsSalary;

}

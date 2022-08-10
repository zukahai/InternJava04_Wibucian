package com.java04.wibucian.dtos;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EmployeeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idEmployee;

    private String name;

    private String address;

    private String srcEmployee;

    private String email;

    private String phoneNumber;

    private String gender;

    private Date birthDay;

    private String maritalStatus;

    private Float coefficientsSalary;

}

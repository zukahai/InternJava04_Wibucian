package com.java04.wibucian.vos;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EmployeeQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

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

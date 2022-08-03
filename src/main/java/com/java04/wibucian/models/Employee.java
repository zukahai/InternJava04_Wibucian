package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    @Column(name = "idEmployee", nullable = false)
    private String idEmployee;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "srcEmployee", nullable = false)
    private String srcEmployee;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "birthDay")
    private Date birthDay;

    @Column(name = "maritalStatus", nullable = false)
    private String maritalStatus;

    @Column(name = "CoefficientsSalary")
    private Float coefficientsSalary;

}

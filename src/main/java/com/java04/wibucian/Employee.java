package com.java04.wibucian;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "idEmployee", nullable = false, length = 15)
    private String id;

    @Column(name = "id", nullable = false)
    private Integer id1;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "srcEmployee", nullable = false, length = 100)
    private String srcEmployee;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "phoneNumber", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "gender", nullable = false, length = 20)
    private String gender;

    @Column(name = "birthDay")
    private Instant birthDay;

    @Column(name = "maritalStatus", nullable = false, length = 50)
    private String maritalStatus;

    @Column(name = "CoefficientsSalary")
    private Double coefficientsSalary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getId1() {
        return id1;
    }

    public void setId1(Integer id1) {
        this.id1 = id1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSrcEmployee() {
        return srcEmployee;
    }

    public void setSrcEmployee(String srcEmployee) {
        this.srcEmployee = srcEmployee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Instant getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Instant birthDay) {
        this.birthDay = birthDay;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Double getCoefficientsSalary() {
        return coefficientsSalary;
    }

    public void setCoefficientsSalary(Double coefficientsSalary) {
        this.coefficientsSalary = coefficientsSalary;
    }

}
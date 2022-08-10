package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmployee", nullable = false, length = 15)
    private String id;

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
    private Date birthDay;

    @Column(name = "maritalStatus", nullable = false, length = 50)
    private String maritalStatus;

    @Column(name = "CoefficientsSalary")
    private Float coefficientsSalary;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<ImportGoods> importGoods = new LinkedHashSet<>();

    @OneToMany(mappedBy = "employeeCreate", fetch = FetchType.LAZY)
    private Set<ShiftRotate> shiftRotatesRequest = new LinkedHashSet<>();

    @OneToMany(mappedBy = "employeeChange", fetch = FetchType.LAZY)
    private Set<ShiftRotate> shiftRotatesAccept = new LinkedHashSet<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<Invoice> invoices = new LinkedHashSet<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<Shift> shifts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "employeeChange", fetch = FetchType.LAZY)
    private Set<Shift> shiftsRotateWork = new LinkedHashSet<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "employee")
    private Account account;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Float getCoefficientsSalary() {
        return coefficientsSalary;
    }

    public void setCoefficientsSalary(Float coefficientsSalary) {
        this.coefficientsSalary = coefficientsSalary;
    }

    public Set<ImportGoods> getImportGoods() {
        return importGoods;
    }

    public void setImportGoods(Set<ImportGoods> importGoods) {
        this.importGoods = importGoods;
    }

    public Set<ShiftRotate> getShiftRotatesRequest() {
        return shiftRotatesRequest;
    }

    public void setShiftRotatesRequest(Set<ShiftRotate> shiftRotatesRequest) {
        this.shiftRotatesRequest = shiftRotatesRequest;
    }

    public Set<ShiftRotate> getShiftRotatesAccept() {
        return shiftRotatesAccept;
    }

    public void setShiftRotatesAccept(Set<ShiftRotate> shiftRotatesAccept) {
        this.shiftRotatesAccept = shiftRotatesAccept;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Set<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(Set<Shift> shifts) {
        this.shifts = shifts;
    }

    public Set<Shift> getShiftsRotateWork() {
        return shiftsRotateWork;
    }

    public void setShiftsRotateWork(Set<Shift> shiftsRotateWork) {
        this.shiftsRotateWork = shiftsRotateWork;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
package com.java04.wibucian.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idShift", nullable = false, length = 15)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmployee")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmployeeChange")
    private Employee employeeChange;


    @Column(name = "isRequestShift")
    private Boolean isRequestShift;

    @Column(name = "isOvertimeRequest")
    private Boolean isOvertimeRequest;

    @Column(name = "requestTime")
    private Instant requestTime;

    @Column(name = "shiftDate")
    private Date shiftDate;

    @Column(name = "shiftCode")
    private Integer shiftCode;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "shift")
    private RollCall rollCall;

    public RollCall getRollCall() {
        return rollCall;
    }

    public void setRollCall(RollCall rollCall) {
        this.rollCall = rollCall;
    }

    public Integer getShiftCode() {
        return shiftCode;
    }

    public void setShiftCode(Integer shiftCode) {
        this.shiftCode = shiftCode;
    }

    public Date getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(Date shiftDate) {
        this.shiftDate = shiftDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployeeChange() {
        return employeeChange;
    }

    public void setEmployeeChange(Employee employeeChange) {
        this.employeeChange = employeeChange;
    }

    public Boolean getRequestShift() {
        return isRequestShift;
    }

    public void setRequestShift(Boolean requestShift) {
        isRequestShift = requestShift;
    }

    public Boolean getOvertimeRequest() {
        return isOvertimeRequest;
    }

    public void setOvertimeRequest(Boolean overtimeRequest) {
        isOvertimeRequest = overtimeRequest;
    }

    public Instant getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Instant requestTime) {
        this.requestTime = requestTime;
    }
}
package com.java04.wibucian.models;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.Instant;
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
    private boolean requestShift;

    @Column(name = "isOvertimeRequest")
    private boolean overtimeRequest;

    @Column(name = "requestTime")
    private Instant requestTime;

    @Column(name = "shiftDate")
    private Date shiftDate;

    @Column(name = "shiftCode")
    private Integer shiftCode;

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
        try {
            int id = Integer.parseInt(this.id);
            return "Shift" + String.format("%05d", id);
        } catch (NumberFormatException e) {
            return this.id;
        }
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

    public boolean getRequestShift() {
        return requestShift;
    }

    public void setRequestShift(boolean requestShift) {
        this.requestShift = requestShift;
    }

    public boolean isOvertimeRequest() {
        return overtimeRequest;
    }

    public void setOvertimeRequest(boolean overtimeRequest) {
        this.overtimeRequest = overtimeRequest;
    }

    public Instant getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Instant requestTime) {
        this.requestTime = requestTime;
    }
}
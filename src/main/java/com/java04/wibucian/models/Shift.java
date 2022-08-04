package com.java04.wibucian.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
public class Shift {
    @Id
    @Column(name = "idShift", nullable = false, length = 15)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmployee")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmployeeChange")
    private Employee employeeChange;

    @Column(name = "timeStart")
    private Instant timeStart;

    @Column(name = "timeEnd")
    private Instant timeEnd;

    @Column(name = "isRequestShift")
    private Boolean isRequestShift;

    @Column(name = "isOvertimeRequest")
    private Boolean isOvertimeRequest;

    @Column(name = "requestTime")
    private Instant requestTime;

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

    public Instant getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Instant timeStart) {
        this.timeStart = timeStart;
    }

    public Instant getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Instant timeEnd) {
        this.timeEnd = timeEnd;
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
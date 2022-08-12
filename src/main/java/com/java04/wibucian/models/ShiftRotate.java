package com.java04.wibucian.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "shiftRotate")
public class ShiftRotate  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idShiftRotate", nullable = false, length = 15)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idShift")
    private Shift shift;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmployee")
    private Employee employeeCreate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmployeeChange")
    private Employee employeeChange;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idShiftExchange")
    private Shift shiftExchange;

    @Column(name = "status")
    private Integer status;

    @Column(name = "createTime")
    private Instant createTime;

    @Column(name = "acceptTime")
    private Instant acceptTime;

    @Column(name = "approveTime")
    private Instant approveTime;

    @Column(name = "rejectTime")
    private Instant rejectTime;

    public Instant getRejectTime() {
        return rejectTime;
    }

    public void setRejectTime(Instant rejectTime) {
        this.rejectTime = rejectTime;
    }

    public Instant getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Instant approveTime) {
        this.approveTime = approveTime;
    }

    public Instant getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Instant acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Shift getShiftExchange() {
        return shiftExchange;
    }

    public void setShiftExchange(Shift idShiftExchange) {
        this.shiftExchange = idShiftExchange;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Employee getEmployeeCreate() {
        return employeeCreate;
    }

    public void setEmployeeCreate(Employee employeeCreate) {
        this.employeeCreate = employeeCreate;
    }

    public Employee getEmployeeChange() {
        return employeeChange;
    }

    public void setEmployeeChange(Employee employeeChange) {
        this.employeeChange = employeeChange;
    }
}
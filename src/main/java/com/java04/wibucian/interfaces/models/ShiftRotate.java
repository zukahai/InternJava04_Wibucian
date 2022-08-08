package com.java04.wibucian.interfaces.models;

import javax.persistence.*;

@Entity
@Table(name = "shiftRotate")
public class ShiftRotate  {
    @Id
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
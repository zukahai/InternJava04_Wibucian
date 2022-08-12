package com.java04.wibucian.models;

import javax.persistence.*;

@Entity
public class MonthlySalary {
    @Id
    @Column(name = "idMonthSalary", nullable = false, length = 15)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmployee")
    private Employee idEmployee;

    @Column(name = "shiftWork")
    private Integer shiftWork;

    @Column(name = "hourWork")
    private Double hourWork;

    @Column(name = "total")
    private Double total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Employee getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Employee idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Integer getShiftWork() {
        return shiftWork;
    }

    public void setShiftWork(Integer shiftWork) {
        this.shiftWork = shiftWork;
    }

    public Double getHourWork() {
        return hourWork;
    }

    public void setHourWork(Double hourWork) {
        this.hourWork = hourWork;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
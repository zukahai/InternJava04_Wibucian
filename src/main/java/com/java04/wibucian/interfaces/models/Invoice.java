package com.java04.wibucian.interfaces.models;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Invoice {
    @Id
    @Column(name = "idInvoice", nullable = false, length = 15)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmployee")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGroupTable")
    private GroupTable groupTable;

    @Column(name = "customerName", nullable = false, length = 50)
    private String customerName;

    @Column(name = "dateTime")
    private Instant dateTime;

    @OneToMany(mappedBy = "invoice")
    private Set<DetailInvoice> detailInvoices = new LinkedHashSet<>();

    @Column(name = "totalMoney")
    private Double totalMoney;

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
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

    public GroupTable getGroupTable() {
        return groupTable;
    }

    public void setGroupTable(GroupTable groupTable) {
        this.groupTable = groupTable;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public Set<DetailInvoice> getDetailInvoices() {
        return detailInvoices;
    }

    public void setDetailInvoices(Set<DetailInvoice> detailInvoices) {
        this.detailInvoices = detailInvoices;
    }
}
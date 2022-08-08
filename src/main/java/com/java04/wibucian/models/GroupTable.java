package com.java04.wibucian.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class GroupTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGroupTable", nullable = false, length = 15)
    private String id;

    @Column(name = "groupName", length = 50)
    private String groupName;

    @Column(name = "foundedTime")
    private Instant foundedTime;

    @OneToMany(mappedBy = "groupTable")
    private Set<Ordercf> ordercfs = new LinkedHashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Instant getFoundedTime() {
        return foundedTime;
    }

    public void setFoundedTime(Instant foundedTime) {
        this.foundedTime = foundedTime;
    }

    public Set<Ordercf> getOrdercfs() {
        return ordercfs;
    }

    public void setOrdercfs(Set<Ordercf> ordercfs) {
        this.ordercfs = ordercfs;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Set<DetailGroupTable> getDetailGroupTables() {
        return detailGroupTables;
    }

    public void setDetailGroupTables(Set<DetailGroupTable> detailGroupTables) {
        this.detailGroupTables = detailGroupTables;
    }

    @OneToMany(mappedBy = "groupTable")
    private Set<Invoice> invoices = new LinkedHashSet<>();

    @OneToMany(mappedBy = "groupTable")
    private Set<DetailGroupTable> detailGroupTables = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "GroupTable{" +
                "id='" + id + '\'' +
                ", groupName='" + groupName + '\'' +
                ", foundedTime=" + foundedTime +
                '}';
    }
}
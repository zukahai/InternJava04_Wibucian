package com.java04.wibucian.models;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ImportGoods")
public class ImportGood {
    @Id
    @Column(name = "idImportGoods", nullable = false, length = 15)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmployee")
    private Employee employee;

    @Column(name = "timeImport")
    private Instant timeImport;

    @OneToMany(mappedBy = "idImportGoods")
    private Set<com.java04.wibucian.models.DetailImportGood> detailImportGoods =
            new LinkedHashSet<>();

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

    public Instant getTimeImport() {
        return timeImport;
    }

    public void setTimeImport(Instant timeImport) {
        this.timeImport = timeImport;
    }

    public Set<com.java04.wibucian.models.DetailImportGood> getDetailImportGoods() {
        return detailImportGoods;
    }

    public void setDetailImportGoods(
            Set<com.java04.wibucian.models.DetailImportGood> detailImportGoods) {
        this.detailImportGoods = detailImportGoods;
    }

}
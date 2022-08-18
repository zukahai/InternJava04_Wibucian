package com.java04.wibucian.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ImportGoods")
public class ImportGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idImportGoods", nullable = false, length = 15)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmployee")
    private Employee employee;

    @Column(name = "timeImport")
    private Instant timeImport;

    @OneToMany(mappedBy = "importGoods")
    private Set<DetailImportGoods> detailImportGoods = new LinkedHashSet<>();


    @Transient
    private String dateFormat;

    public String getDateFormat() {
        return this.timeImport == null ? null : new SimpleDateFormat("dd-MM-yyyy").format(Date.from(this.timeImport));
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

    public Instant getTimeImport() {
        return timeImport;
    }

    public void setTimeImport(Instant timeImport) {
        this.timeImport = timeImport;
    }

    public Set<DetailImportGoods> getDetailImportGoods() {
        return detailImportGoods;
    }

    public void setDetailImportGoods(Set<DetailImportGoods> detailImportGoods) {
        this.detailImportGoods = detailImportGoods;
    }


}
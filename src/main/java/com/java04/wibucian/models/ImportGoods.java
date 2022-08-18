package com.java04.wibucian.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
    private Date timeImport;

    @OneToMany(mappedBy = "importGoods")
    private Set<DetailImportGoods> detailImportGoods = new LinkedHashSet<>();


    @Transient
    private String dateFormat;

    public String getDateFormat() {
        return this.timeImport == null ? null : new SimpleDateFormat("dd-MM-yyyy").format(this.timeImport);
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

    public Date getTimeImport() {
        return timeImport;
    }

    public void setTimeImport(Date timeImport) {
        this.timeImport = timeImport;
    }

    public Set<DetailImportGoods> getDetailImportGoods() {
        return detailImportGoods;
    }

    public void setDetailImportGoods(Set<DetailImportGoods> detailImportGoods) {
        this.detailImportGoods = detailImportGoods;
    }


}
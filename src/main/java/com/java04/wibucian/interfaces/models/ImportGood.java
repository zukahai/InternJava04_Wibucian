package com.java04.wibucian.models;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "ImportGoods")
public class ImportGood {
    @Id
    @Column(name = "idImportGoods", nullable = false, length = 15)
    private String id;

    @Column(name = "id", nullable = false)
    private Integer id1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmployee")
    private Employee idEmployee;

    @Column(name = "timeImport")
    private Instant timeImport;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getId1() {
        return id1;
    }

    public void setId1(Integer id1) {
        this.id1 = id1;
    }

    public Employee getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Employee idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Instant getTimeImport() {
        return timeImport;
    }

    public void setTimeImport(Instant timeImport) {
        this.timeImport = timeImport;
    }

}
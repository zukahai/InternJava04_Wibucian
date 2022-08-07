package com.java04.wibucian.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
public class DetailGroupTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetailGroupTable", nullable = false, length = 15)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGroupTable")
    private GroupTable groupTable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTablecf")
    private Tablecf tablecf;

    @Column(name = "groupTime")
    private Instant groupTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GroupTable getGroupTable() {
        return groupTable;
    }

    public void setGroupTable(GroupTable groupTable) {
        this.groupTable = groupTable;
    }

    public Tablecf getTablecf() {
        return tablecf;
    }

    public void setTablecf(Tablecf tablecf) {
        this.tablecf = tablecf;
    }

    public Instant getGroupTime() {
        return groupTime;
    }

    public void setGroupTime(Instant groupTime) {
        this.groupTime = groupTime;
    }

}
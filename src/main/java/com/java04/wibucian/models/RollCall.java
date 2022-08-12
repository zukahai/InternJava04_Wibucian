package com.java04.wibucian.models;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class RollCall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInvoice", nullable = false, length = 15)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idShift")
    private Shift shift;

    @Column(name = "rollCallTime")
    private Instant rollCallTime;

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

    public Instant getRollCallTime() {
        return rollCallTime;
    }

    public void setRollCallTime(Instant rollCallTime) {
        this.rollCallTime = rollCallTime;
    }

}
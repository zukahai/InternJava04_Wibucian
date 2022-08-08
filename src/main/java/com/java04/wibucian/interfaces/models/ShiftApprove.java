package com.java04.wibucian.models;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class ShiftApprove {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idShiftApprove", nullable = false, length = 15)
    private String id;

    @Column(name = "approveTime")
    private Instant approveTime;

    @Column(name = "approveFor")
    private Date approveFor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Instant approveTime) {
        this.approveTime = approveTime;
    }

    public Date getApproveFor() {
        return approveFor;
    }

    public void setApproveFor(Date approveFor) {
        this.approveFor = approveFor;
    }

}
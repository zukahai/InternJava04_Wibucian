package com.java04.wibucian.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class WorkPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idWorkPlan", nullable = false, length = 15)
    private String id;

    @Column(name = "shiftDate")
    private Date shiftDate;

    @Column(name = "shiftCode")
    private Integer shiftCode;

    @Column(name = "\"work\"")
    private Boolean work;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(Date shiftDate) {
        this.shiftDate = shiftDate;
    }

    public Integer getShiftCode() {
        return shiftCode;
    }

    public void setShiftCode(Integer shiftCode) {
        this.shiftCode = shiftCode;
    }

    public Boolean getWork() {
        return work;
    }

    public void setWork(Boolean work) {
        this.work = work;
    }

}
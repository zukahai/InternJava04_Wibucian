package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Shift")
public class Shift implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    @Column(name = "idShift", nullable = false)
    private String idShift;

    @Column(name = "idEmployee")
    private String idEmployee;

    @Column(name = "idEmployeeChange")
    private String idEmployeeChange;

    @Column(name = "timeStart")
    private Date timeStart;

    @Column(name = "timeEnd")
    private Date timeEnd;

}

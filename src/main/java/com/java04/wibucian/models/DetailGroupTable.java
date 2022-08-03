package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "DetailGroupTable")
public class DetailGroupTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    @Column(name = "idDetailGroupTable", nullable = false)
    private String idDetailGroupTable;

    @Column(name = "idGroupTable")
    private String idGroupTable;

    @Column(name = "idTablecf")
    private String idTablecf;

    @Column(name = "groupTime")
    private Date groupTime;

}

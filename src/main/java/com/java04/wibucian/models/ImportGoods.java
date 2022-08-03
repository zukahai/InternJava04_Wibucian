package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "ImportGoods")
public class ImportGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    @Column(name = "idImportGoods", nullable = false)
    private String idImportGoods;

    @Column(name = "idEmployee")
    private String idEmployee;

    @Column(name = "timeImport")
    private Date timeImport;

}

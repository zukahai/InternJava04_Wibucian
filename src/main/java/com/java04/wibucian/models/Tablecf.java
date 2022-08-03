package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Tablecf")
public class Tablecf implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    @Column(name = "idTablecf", nullable = false)
    private String idTablecf;

    @Column(name = "idTypeTable")
    private String idTypeTable;

    @Column(name = "describe")
    private String describe;

    @Column(name = "capacity")
    private Integer capacity;

}

package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "TypeTable")
public class TypeTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    @Column(name = "idTypeTable", nullable = false)
    private String idTypeTable;

    @Column(name = "typeName", nullable = false)
    private String typeName;

    @Column(name = "price")
    private Float price;

}

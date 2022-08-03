package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "TypeProduct")
public class TypeProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    @Column(name = "idTypeProduct", nullable = false)
    private String idTypeProduct;

    @Column(name = "productName", nullable = false)
    private String productName;

}

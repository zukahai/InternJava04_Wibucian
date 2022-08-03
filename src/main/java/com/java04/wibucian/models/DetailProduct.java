package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "DetailProduct")
public class DetailProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    @Column(name = "idDetailProduct", nullable = false)
    private String idDetailProduct;

    @Column(name = "idProduct")
    private String idProduct;

    @Column(name = "idIngredient")
    private String idIngredient;

    @Column(name = "quantity")
    private Integer quantity;

}

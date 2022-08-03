package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Ingredient")
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    @Column(name = "idIngredient", nullable = false)
    private String idIngredient;

    @Column(name = "ingredientName", nullable = false)
    private String ingredientName;

    @Column(name = "expiryIngredient")
    private Date expiryIngredient;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "unit", nullable = false)
    private String unit;

}

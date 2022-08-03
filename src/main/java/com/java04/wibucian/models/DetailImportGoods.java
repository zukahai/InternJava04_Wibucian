package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "DetailImportGoods")
public class DetailImportGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    @Column(name = "idDetailImportGoods", nullable = false)
    private String idDetailImportGoods;

    @Column(name = "idIngredient")
    private String idIngredient;

    @Column(name = "idImportGoods")
    private String idImportGoods;

    @Column(name = "quantity")
    private Integer quantity;

}

package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Ordercf")
public class OrdercfNoMapPing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    @Column(name = "idOrdercf", nullable = false)
    private String idOrdercf;

    @Column(name = "idGroupTable")
    private String idGroupTable;

    @Column(name = "idProduct")
    private String idProduct;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "timeOrder")
    private Date timeOrder;

    @Column(name = "status")
    private Integer status;

}

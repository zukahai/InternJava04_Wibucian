package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Product")
public class ProductNoMapPing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    @Column(name = "idProduct", nullable = false)
    private String idProduct;

    @Column(name = "idSale")
    private String idSale;

    @Column(name = "idProductType")
    private String idProductType;

    @Column(name = "\"describe\"")
    private String describe;

    @Column(name = "srcImage", nullable = false)
    private String srcImage;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "price")
    private Float price;

}

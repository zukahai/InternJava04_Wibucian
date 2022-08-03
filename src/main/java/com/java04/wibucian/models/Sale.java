package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Sale")
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    @Column(name = "idSale", nullable = false)
    private String idSale;

    @Column(name = "pcent", nullable = false)
    private String pcent;

    @Column(name = "price")
    private Float price;

}

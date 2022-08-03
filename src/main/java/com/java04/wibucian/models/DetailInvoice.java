package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "DetailInvoice")
public class DetailInvoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    @Column(name = "idDetailInvoice", nullable = false)
    private String idDetailInvoice;

    @Column(name = "idInvoice")
    private String idInvoice;

    @Column(name = "idProduct")
    private String idProduct;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "dateTime")
    private Date dateTime;

    @Column(name = "totalMoney")
    private Float totalMoney;

}

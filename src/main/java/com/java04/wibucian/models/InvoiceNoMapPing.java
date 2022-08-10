package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Invoice")
public class InvoiceNoMapPing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    @Column(name = "idInvoice", nullable = false)
    private String idInvoice;

    @Column(name = "idEmployee")
    private String idEmployee;

    @Column(name = "idGroupTable")
    private String idGroupTable;

    @Column(name = "customerName", nullable = false)
    private String customerName;

    @Column(name = "dateTime")
    private Date dateTime;

    @Column(name = "totalMoney")
    private Float totalMoney;

}

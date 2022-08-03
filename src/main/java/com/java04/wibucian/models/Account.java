package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    @Column(name = "idAccount", nullable = false)
    private String idAccount;

    @Column(name = "idEmployee")
    private String idEmployee;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Integer role;

}

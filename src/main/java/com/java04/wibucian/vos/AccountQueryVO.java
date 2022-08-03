package com.java04.wibucian.vos;


import lombok.Data;

import java.io.Serializable;

@Data
public class AccountQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idAccount;

    private String idEmployee;

    private String password;

    private Integer role;

}

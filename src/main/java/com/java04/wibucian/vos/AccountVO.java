package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class AccountVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idAccount;

    private String idEmployee;

    private String password;

    private Integer role;

}

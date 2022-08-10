package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class TypeProductVO implements Serializable {
    private static final long serialVersionUID = 1L;

       @NotNull(message = "productName can not null")
    private String productName;



}

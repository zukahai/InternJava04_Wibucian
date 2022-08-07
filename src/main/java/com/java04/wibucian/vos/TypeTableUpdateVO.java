package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class TypeTableUpdateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idTypeTable;

    @NotNull(message = "typeName can not null")
    private String typeName;

    private Double price;

}
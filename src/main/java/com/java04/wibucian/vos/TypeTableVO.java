package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class TypeTableVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "idTypeTable can not null")
    private String idTypeTable;

    @NotNull(message = "typeName can not null")
    private String typeName;

    private Float price;

}

package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class TablecfVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idTypeTable;

    private String describe;

    private Integer capacity;

}

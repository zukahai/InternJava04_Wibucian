package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class OrdercfVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idOrdercf;

    private String idGroupTable;

    private String idProduct;

    private Integer quantity;

    private Date timeOrder;

    private Integer status;

}

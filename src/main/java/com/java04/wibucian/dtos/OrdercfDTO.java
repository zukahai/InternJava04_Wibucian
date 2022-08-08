package com.java04.wibucian.dtos;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrdercfDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String idOrdercf;

    private String idGroupTable;

    private String idProduct;

    private Integer quantity;

    private Date timeOrder;

    private Integer status;

}

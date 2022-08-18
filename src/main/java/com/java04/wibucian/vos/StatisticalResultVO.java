package com.java04.wibucian.vos;

import lombok.Data;

import java.io.Serializable;
@Data
public class StatisticalResultVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idProduct;
    private String nameProduct;
    private Integer quantity;
    private Double totalMoney;
    private Double profitMoney;
}

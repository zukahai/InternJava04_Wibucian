package com.java04.wibucian.vos;

import lombok.Data;

import java.io.Serializable;
@Data
public class StatisticalVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String timeStart;
    private String timeEnd;
}



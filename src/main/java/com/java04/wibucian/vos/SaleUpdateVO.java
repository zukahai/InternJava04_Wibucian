package com.java04.wibucian.vos;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class SaleUpdateVO extends SaleVO implements Serializable {
    private static final long serialVersionUID = 1L;

}

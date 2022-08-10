package com.java04.wibucian.vos;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class EmployeeUpdateVO extends EmployeeVO implements Serializable {
    private static final long serialVersionUID = 1L;
    public String toString (){
        return super.toString();
    }

}
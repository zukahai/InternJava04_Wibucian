package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class GroupTableVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String groupName;

    private Date foundedTime;

}

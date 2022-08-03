package com.java04.wibucian.dtos;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GroupTableDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String idGroupTable;

    private String groupName;

    private Date foundedTime;

}

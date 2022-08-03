package com.java04.wibucian.vos;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GroupTableQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idGroupTable;

    private String groupName;

    private Date foundedTime;

}

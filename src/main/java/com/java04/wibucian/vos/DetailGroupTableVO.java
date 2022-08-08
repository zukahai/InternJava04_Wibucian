package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;


@Data
public class DetailGroupTableVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idGroupTable;

    private String idTablecf;

    private Instant groupTime;

}

package com.java04.wibucian.vos;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;


public class DetailIngredientVO {
    private Float valueChange;

    @NotNull(message = "content can not null")
    private String content;

    private Instant dateTime;

}

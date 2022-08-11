package com.java04.wibucian.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Data
public class SaleVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "pcent can not null")
    private String pcent;

    private String timeStart;

    private String timeEnd;

    public Date getTimeStart() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(this.timeStart);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }

    public Date getTimeEnd() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(this.timeEnd);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }
}

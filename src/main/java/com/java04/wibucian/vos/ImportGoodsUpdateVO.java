package com.java04.wibucian.vos;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class ImportGoodsUpdateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Ngày nhập hàng là bắt buoc")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String timeImport;

    public Instant getTimeImport() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(this.timeImport).toInstant();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

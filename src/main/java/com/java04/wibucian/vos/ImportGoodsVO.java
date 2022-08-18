package com.java04.wibucian.vos;

import com.java04.wibucian.commons.Constant;
import com.java04.wibucian.commons.Utils;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class ImportGoodsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String idImportGoods;

    private String idEmployee;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String timeImport;

    public Date getTimeImport() {
        return Utils.getCalendarInstanceFromFormat(this.timeImport, Constant.YYYY_MM_DD_FORMAT).getTime();
    }

}

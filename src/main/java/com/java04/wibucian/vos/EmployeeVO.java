package com.java04.wibucian.vos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import net.bytebuddy.utility.nullability.UnknownNull;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;


@Data
public class EmployeeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "name can not null")
    private String name;

    @NotNull(message = "address can not null")
    private String address;

    private String srcEmployee;

    private MultipartFile avatar;

    @NotNull(message = "email can not null")
    private String email;

    @NotNull(message = "phoneNumber can not null")
    private String phoneNumber;

    @NotNull(message = "gender can not null")
    private String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    @NotNull(message = "maritalStatus can not null")
    private String maritalStatus;

    private Double coefficientsSalary;

    public Date getBirthDay() {
        System.out.println(this.birthDay);
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(this.birthDay);
        } catch(ParseException e) {
            throw new RuntimeException("Ngày không hợp lệ");
        }
    }
}

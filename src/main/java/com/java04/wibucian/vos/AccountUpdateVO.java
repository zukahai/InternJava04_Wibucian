package com.java04.wibucian.vos;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class AccountUpdateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Mật khẩu cũ là bắt buộc")
    private String oldPassword;

    @NotNull(message = "Mật khẩu mới là bắt buộc")
//    @Pattern()
    private String newPassword;

    @NotNull(message = "Mật khẩu nhắc lại là bắt buộc")
    private String repeatNewPassword;
}

package com.java04.wibucian.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePassword {
    private String userName;
    private String oldPassword;
    private String password;
}

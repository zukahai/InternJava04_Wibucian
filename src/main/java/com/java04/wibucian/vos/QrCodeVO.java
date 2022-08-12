package com.java04.wibucian.vos;

import lombok.Data;

import java.io.Serializable;

@Data
public class QrCodeVO implements Serializable {
    String content;
    int width;
    int height;
}

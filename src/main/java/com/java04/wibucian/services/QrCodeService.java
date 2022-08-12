package com.java04.wibucian.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Logger;

@Service

public class QrCodeService {
    private Logger logger = Logger.getLogger(QrCodeService.class.getName());


    public String generateQRCodeBase64(String qrCodeContent, int width, int height) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeContent, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
            return  Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (WriterException | IOException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }
}

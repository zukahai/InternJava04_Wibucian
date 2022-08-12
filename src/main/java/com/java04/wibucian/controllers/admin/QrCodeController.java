package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.services.QrCodeService;
import com.java04.wibucian.vos.QrCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
public class QrCodeController {
    @Autowired
    QrCodeService qrCodeService;

    @PostMapping(value = "/qrcode",
            consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> generateQRCodeBase64(@RequestBody QrCodeVO qrCodeVO) throws UnsupportedEncodingException {
        //encode the content to unicode
        String qrCodeContent = qrCodeVO.getContent();
        //ả -> 1EA2
        qrCodeContent = new String(qrCodeContent.getBytes("UTF-8"), "ISO-8859-1");
        System.out.println(qrCodeContent);
        return ResponseEntity.ok(qrCodeService.generateQRCodeBase64(qrCodeContent, qrCodeVO.getWidth(), qrCodeVO.getHeight()));
    }
}

package org.example.generateqr_code.controller;

import org.example.generateqr_code.entity.Info;
import org.example.generateqr_code.service.QrCode;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeController {
    @GetMapping("QR-Code")
    public ResponseEntity<InputStreamSource>getCode(@RequestBody Info info){
        HttpHeaders httpHeader= new HttpHeaders();
        httpHeader.add(HttpHeaders.CONTENT_DISPOSITION,"inline; fileName=Qr-Code.png");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeader).contentType( MediaType.IMAGE_PNG).body(QrCode.getCode(info));
    }
}

package org.example.generateqr_code.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.example.generateqr_code.entity.Info;
import org.springframework.core.io.InputStreamResource;
import com.google.zxing.qrcode.QRCodeWriter;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class QrCode {


    public static InputStreamResource getCode(Info info) {
         int with = 300;//new Random().nextInt(200,400);
         int height =300;// new Random().nextInt(200,400);

        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(
                    info.toString(),
                    BarcodeFormat.QR_CODE,
                    with,
                    height,
                    createHintMap()
            );

            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            byte[] imageBytes = convertImageToByteArray(bufferedImage);
            ByteArrayInputStream byteArrayInputStream= new ByteArrayInputStream(imageBytes);
            return new InputStreamResource(byteArrayInputStream);
        }catch (WriterException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed generate QR code", e);
        }

    }

private static Map<EncodeHintType, Object> createHintMap() {
    Map<EncodeHintType, Object> hintMap = new HashMap<>();
    hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
    return hintMap;
}

    private static byte[] convertImageToByteArray(BufferedImage bufferedImage) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to convert image to byte array", e);
        }
    }
}

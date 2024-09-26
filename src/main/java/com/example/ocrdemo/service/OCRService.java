package com.example.ocrdemo.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class OCRService {

    private final Tesseract tesseract;

    public OCRService() {
        tesseract = new Tesseract();
        tesseract.setDatapath(
                "C:\\Users\\admin\\Desktop\\JKU\\my projects\\Springboot+Angular\\demo\\demo\\src\\main\\resources\\tessdata"); // Path
        // to
        // the
        // Tesseract
        // data
        // folder
        tesseract.setLanguage("eng"); // Set language to English
    }

    public String extractText(BufferedImage image) {
        try {
            return tesseract.doOCR(image);
        } catch (TesseractException e) {
            return "Error during OCR processing: " + e.getMessage();
        }
    }
}

package com.example.ocrdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.example.ocrdemo.service.OCRService;

@CrossOrigin(origins = "http://localhost:4200") // Allow CORS for this specific controller
@RestController
@RequestMapping("/api")
public class OCRController {

    private final OCRService ocrService;

    public OCRController(OCRService ocrService) {
        this.ocrService = ocrService;
    }

    @PostMapping("/extract-text")
    public ResponseEntity<String> extractTextFromImage(@RequestParam("image") MultipartFile imageFile) {
        try {
            BufferedImage image = ImageIO.read(imageFile.getInputStream());
            if (image == null) {
                return ResponseEntity.badRequest().body("Invalid image file.");
            }
            String extractedText = ocrService.extractText(image);
            return ResponseEntity.ok(extractedText);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading image file.");
        }
    }

}

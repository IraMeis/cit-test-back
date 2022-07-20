package com.morena.citTestBack.controller;

import com.morena.citTestBack.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PatchMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile file) {
        return fileService.solveFile(file);
    }

    @PatchMapping("/download")
    public ResponseEntity<?> download() {
        return null;
    }
}

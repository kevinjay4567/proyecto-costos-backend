package org.kevin.backendcostos.controllers;

import lombok.RequiredArgsConstructor;
import org.kevin.backendcostos.entities.File;
import org.kevin.backendcostos.services.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;

    @GetMapping("/files")
    public ResponseEntity<List<File>> getAllFiles() {
        return ResponseEntity.ok(fileService.getAllFiles());
    }

    @PostMapping("/file")
    public ResponseEntity<String> storeFile(@RequestPart("file") MultipartFile file) {
        if (file == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al leer el archivo");
        return fileService.storeFile(file);
    }
}

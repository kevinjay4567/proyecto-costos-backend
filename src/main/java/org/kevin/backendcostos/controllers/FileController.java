package org.kevin.backendcostos.controllers;

import lombok.RequiredArgsConstructor;
import org.kevin.backendcostos.dto.OkResponseDto;
import org.kevin.backendcostos.entities.Agrupacion;
import org.kevin.backendcostos.entities.Balance;
import org.kevin.backendcostos.entities.DatosPersonas;
import org.kevin.backendcostos.entities.File;
import org.kevin.backendcostos.repositories.AgrupacionesRepository;
import org.kevin.backendcostos.repositories.DatosPersonasRepository;
import org.kevin.backendcostos.services.BalanceService;
import org.kevin.backendcostos.services.DatosPersonasService;
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
    private final DatosPersonasService datosPersonasService;
    private final BalanceService balanceService;
    private final AgrupacionesRepository agrupacionesRepository;
    private final DatosPersonasRepository datosPersonasRepository;

    @GetMapping("/files")
    public ResponseEntity<List<File>> getAllFiles() {
        return ResponseEntity.ok(fileService.getAllFiles());
    }

    @GetMapping("/files/agrupaciones")
    public ResponseEntity<List<Agrupacion>> getAllAgrupaciones() {
        return ResponseEntity.ok(agrupacionesRepository.findAll());
    }

    @GetMapping("/files/balances")
    public ResponseEntity<List<Balance>> getAllBalances() {
        return ResponseEntity.ok(balanceService.getAll());
    }

    @GetMapping("/files/datospersonas")
    public ResponseEntity<List<DatosPersonas>> getAllDatosPersonas() {
        return ResponseEntity.ok(datosPersonasRepository.findAll());
    }

    @PostMapping("file/datospersonas")
    public ResponseEntity<String> storeFileDatosPersonas(@RequestPart("file") MultipartFile file, int anho) {
        if (file == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al leer el archivo");
        return datosPersonasService.storeFile(file, anho);
    }

    @PostMapping("/file")
    public ResponseEntity<String> storeFile(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OkResponseDto("Error al leer el archivo").toString());
        return fileService.storeFile(file);
    }
}

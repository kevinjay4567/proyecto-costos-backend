package org.kevin.backendcostos.controllers;

import lombok.RequiredArgsConstructor;
import org.kevin.backendcostos.entities.BalanceEntity;
import org.kevin.backendcostos.entities.DatosPersonaEntity;
import org.kevin.backendcostos.response.BalanceResponse;
import org.kevin.backendcostos.response.DatosPersonaResponse;
import org.kevin.backendcostos.services.balance.BalanceServiceImpl;
import org.kevin.backendcostos.services.datospersona.DatosPersonaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/files")
public class FileController {

    private final BalanceServiceImpl balanceService;
    private final DatosPersonaServiceImpl datosPersonaService;

    @GetMapping("/balances")
    public ResponseEntity<List<BalanceEntity>> getAllBalances() {
        return ResponseEntity.ok(balanceService.getAll());
    }
    @PostMapping("/balance")
    public ResponseEntity<BalanceResponse> postBalance(@RequestPart("file") MultipartFile file) {
        return balanceService.store(file);
    }

    @GetMapping("/datospersonas")
    public ResponseEntity<List<DatosPersonaEntity>> getAllDatosPersonas() {
        return ResponseEntity.ok(datosPersonaService.getAll());
    }

    @PostMapping("/datospersona")
    public ResponseEntity<DatosPersonaResponse> postDatosPersona(@RequestPart("file") MultipartFile file) {
        return datosPersonaService.store(file);
    }
}
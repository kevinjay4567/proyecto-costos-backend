package org.kevin.backendcostos.controllers;

import lombok.RequiredArgsConstructor;
import org.kevin.backendcostos.entities.BalanceEntity;
import org.kevin.backendcostos.entities.DatosPersonaEntity;
import org.kevin.backendcostos.entities.PTAEntity;
import org.kevin.backendcostos.entities.UnimediosEntity;
import org.kevin.backendcostos.response.BalanceResponse;
import org.kevin.backendcostos.response.DatosPersonaResponse;
import org.kevin.backendcostos.services.PTA.PTAServiceImpl;
import org.kevin.backendcostos.services.balance.BalanceServiceImpl;
import org.kevin.backendcostos.services.datospersona.DatosPersonaServiceImpl;
import org.kevin.backendcostos.services.unimedios.UnimediosServiceImpl;
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
    private final PTAServiceImpl ptaService;
    private final UnimediosServiceImpl unimediosService;

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

    @GetMapping("/unimedios")
    public ResponseEntity<List<UnimediosEntity>> getAllUnimedios() {
        return ResponseEntity.ok(unimediosService.getAll());
    }

    @PostMapping("/datospersona")
    public ResponseEntity<DatosPersonaResponse> postDatosPersona(@RequestPart("file") MultipartFile file) {
        return datosPersonaService.store(file);
    }

    @GetMapping("/ptas")
    public ResponseEntity<List<PTAEntity>> getAllPTAs() {
        return ResponseEntity.ok(ptaService.getAll());
    }

    @PostMapping("/pta")
    public ResponseEntity<String> postPTA(@RequestPart("file") MultipartFile file) {
        return ptaService.store(file);
    }

    @PostMapping("/unimedios")
    public ResponseEntity<String> postUnimedios(@RequestPart("file") MultipartFile file) {
        return unimediosService.store(file);
    }
}
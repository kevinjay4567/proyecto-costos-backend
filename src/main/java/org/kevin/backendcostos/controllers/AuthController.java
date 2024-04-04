package org.kevin.backendcostos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Hello world");
    }

    @GetMapping("/auth/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login Page");
    }
}

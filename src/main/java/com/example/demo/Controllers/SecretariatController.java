package com.example.demo.Controllers;

import com.example.demo.DTO.SecretariatDTO;
import com.example.demo.Services.SercretariatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/secretariats")
public class SecretariatController {

    private final SercretariatService secretariatService;

    @Autowired
    public SecretariatController(SercretariatService secretariatService) {
        this.secretariatService = secretariatService;
    }

    // Crear una nueva secretaría
    @PostMapping
    public ResponseEntity<SecretariatDTO> createSecretariat(@RequestBody SecretariatDTO secretariatDTO) {
        SecretariatDTO createdSecretariat = secretariatService.createSecretariat(secretariatDTO);
        return ResponseEntity.ok(createdSecretariat);
    }
}

package com.example.demo.Controllers;

import com.example.demo.DTO.SecretariatDTO;
import com.example.demo.Services.SercretariatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestController
@RequestMapping("/api/secretariats")
public class SecretariatController {

    private final SercretariatService secretariatService;

    @Autowired
    public SecretariatController(SercretariatService secretariatService) {
        this.secretariatService = secretariatService;
    }

    // Método auxiliar para invocar métodos privados usando reflexión
    @SuppressWarnings("unchecked")
    private <T> T invokePrivateMethod(String methodName, Class<?>[] paramTypes, Object... args) {
        try {
            Method method = SercretariatService.class.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            return (T) method.invoke(secretariatService, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error al invocar el método privado: " + methodName, e);
        }
    }

    // Crear una nueva secretaría
    @PostMapping
    public ResponseEntity<SecretariatDTO> createSecretariat(@RequestBody SecretariatDTO secretariatDTO) {
        SecretariatDTO createdSecretariat = invokePrivateMethod(
            "createSecretariat", 
            new Class<?>[]{SecretariatDTO.class}, 
            secretariatDTO
        );
        return ResponseEntity.ok(createdSecretariat);
    }
}

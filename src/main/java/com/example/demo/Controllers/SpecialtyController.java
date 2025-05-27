package com.example.demo.Controllers;

import com.example.demo.DTO.SpecialtyDTO;
import com.example.demo.Services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@RestController
@RequestMapping("/api/specialties")
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @Autowired
    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    // Método auxiliar para invocar métodos privados usando reflexión
    @SuppressWarnings("unchecked")
    private <T> T invokePrivateMethod(String methodName, Class<?>[] paramTypes, Object... args) {
        try {
            Method method = SpecialtyService.class.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            return (T) method.invoke(specialtyService, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error al invocar el método privado: " + methodName, e);
        }
    }

    // Obtener todas las especialidades
    @GetMapping
    public ResponseEntity<List<SpecialtyDTO>> getAllSpecialties() {
        List<SpecialtyDTO> specialties = specialtyService.findAll();
        return ResponseEntity.ok(specialties);
    }

    // Crear una nueva especialidad
    @PostMapping
    public ResponseEntity<SpecialtyDTO> createSpecialty(@RequestBody SpecialtyDTO specialtyDTO) {
        SpecialtyDTO createdSpecialty = invokePrivateMethod(
            "create", 
            new Class<?>[]{SpecialtyDTO.class}, 
            specialtyDTO
        );
        return ResponseEntity.ok(createdSpecialty);
    }
}

package com.example.demo.Controllers;

import com.example.demo.DTO.HistoricalRecordDTO;
import com.example.demo.Services.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestController
@RequestMapping("/api/medical-records")
public class HrController {

    private final HrService hrService;

    @Autowired
    public HrController(HrService hrService) {
        this.hrService = hrService;
    }

    // Método auxiliar para invocar métodos privados usando reflexión
    @SuppressWarnings("unchecked")
    private <T> T invokePrivateMethod(String methodName, Class<?>[] paramTypes, Object... args) {
        try {
            Method method = HrService.class.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            return (T) method.invoke(hrService, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error al invocar el método privado: " + methodName, e);
        }
    }

    // Crear un nuevo registro médico
    @PostMapping
    public ResponseEntity<HistoricalRecordDTO> createHr(@RequestBody HistoricalRecordDTO hrDTO) {
        HistoricalRecordDTO createdHr = invokePrivateMethod(
            "createHr", 
            new Class<?>[]{HistoricalRecordDTO.class}, 
            hrDTO
        );
        return ResponseEntity.ok(createdHr);
    }

    // Actualizar un registro médico existente
    @PutMapping("/{id}")
    public ResponseEntity<HistoricalRecordDTO> updateHr(
            @PathVariable Long id, 
            @RequestBody HistoricalRecordDTO hrDTO) {
        HistoricalRecordDTO updatedHr = invokePrivateMethod(
            "updateHr", 
            new Class<?>[]{Long.class, HistoricalRecordDTO.class}, 
            id, hrDTO
        );
        return ResponseEntity.ok(updatedHr);
    }
}

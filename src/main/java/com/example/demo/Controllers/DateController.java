package com.example.demo.Controllers;

import com.example.demo.DTO.DateDTO;
import com.example.demo.Services.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestController
@RequestMapping("/api/dates")
public class DateController {

    private final DateService dateService;

    @Autowired
    public DateController(DateService dateService) {
        this.dateService = dateService;
    }

    // Método auxiliar para invocar métodos privados usando reflexión
    @SuppressWarnings("unchecked")
    private <T> T invokePrivateMethod(String methodName, Class<?>[] paramTypes, Object... args) {
        try {
            Method method = DateService.class.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            return (T) method.invoke(dateService, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error al invocar el método privado: " + methodName, e);
        }
    }

    // Crear una nueva cita
    @PostMapping
    public ResponseEntity<DateDTO> createDate(@RequestBody DateDTO dateDTO) {
        DateDTO createdDate = invokePrivateMethod(
            "createDate", 
            new Class<?>[]{DateDTO.class}, 
            dateDTO
        );
        return ResponseEntity.ok(createdDate);
    }

    // Obtener una cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<DateDTO> getDateById(@PathVariable Long id) {
        DateDTO date = invokePrivateMethod(
            "getDateById", 
            new Class<?>[]{Long.class}, 
            id
        );
        return ResponseEntity.ok(date);
    }

    // Actualizar una cita existente
    @PutMapping("/{id}")
    public ResponseEntity<DateDTO> updateDate(
            @PathVariable Long id, 
            @RequestBody DateDTO dateDTO) {
        DateDTO updatedDate = invokePrivateMethod(
            "updateDate", 
            new Class<?>[]{Long.class, DateDTO.class}, 
            id, dateDTO
        );
        return ResponseEntity.ok(updatedDate);
    }
}

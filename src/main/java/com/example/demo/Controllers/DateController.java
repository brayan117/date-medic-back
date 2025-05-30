package com.example.demo.Controllers;

import com.example.demo.DTO.DateDTO;
import com.example.demo.Services.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dates")
public class DateController {

    private final DateService dateService;

    @Autowired
    public DateController(DateService dateService) {
        this.dateService = dateService;
    }

    // Crear una nueva cita
    @PostMapping
    public ResponseEntity<DateDTO> createDate(@RequestBody DateDTO dateDTO) {
        DateDTO createdDate = dateService.createDate(dateDTO);
        return ResponseEntity.ok(createdDate);
    }
    
    // Obtener cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<DateDTO> getDate(@PathVariable Long id) {
        DateDTO date = dateService.getDateById(id);
        return ResponseEntity.ok(date);
    }
    
    // Actualizar cita
    @PutMapping("/{id}")
    public ResponseEntity<DateDTO> updateDate(
            @PathVariable Long id, 
            @RequestBody DateDTO dateDTO) {
        DateDTO updatedDate = dateService.updateDate(id, dateDTO);
        return ResponseEntity.ok(updatedDate);
    }
}

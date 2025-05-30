package com.example.demo.Controllers;

import com.example.demo.DTO.HistoricalRecordDTO;
import com.example.demo.Services.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medical-records")
public class HrController {

    private final HrService hrService;

    @Autowired
    public HrController(HrService hrService) {
        this.hrService = hrService;
    }

    // Crear un nuevo registro médico
    @PostMapping
    public ResponseEntity<HistoricalRecordDTO> createHr(@RequestBody HistoricalRecordDTO hrDTO) {
        HistoricalRecordDTO createdHr = hrService.createHr(hrDTO);
        return ResponseEntity.ok(createdHr);
    }

    // Actualizar un registro médico existente
    @PutMapping("/{id}")
    public ResponseEntity<HistoricalRecordDTO> updateHr(
            @PathVariable Long id, 
            @RequestBody HistoricalRecordDTO hrDTO) {
        HistoricalRecordDTO updatedHr = hrService.updateHr(id, hrDTO);
        return ResponseEntity.ok(updatedHr);
    }
}

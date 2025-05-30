package com.example.demo.Controllers;

import com.example.demo.DTO.PatientDTO;
import com.example.demo.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Obtener un paciente por ID
    @GetMapping("/{cc}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long cc) {
        PatientDTO patientDTO = patientService.getPatientById(cc);
        return ResponseEntity.ok(patientDTO);
    }

    // Obtener todos los pacientes
    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    // Crear un nuevo paciente
    @PostMapping
    public ResponseEntity<?> createPatient(@RequestBody PatientDTO patientDTO) {
        try {
            PatientDTO createdPatient = patientService.createPatient(patientDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    // Eliminar un paciente
    @DeleteMapping("/{cc}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long cc) {
        patientService.deletePatient(cc);
        return ResponseEntity.noContent().build();
    }
}

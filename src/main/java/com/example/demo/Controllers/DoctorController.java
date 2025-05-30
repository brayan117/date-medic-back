package com.example.demo.Controllers;

import com.example.demo.DTO.DoctorDTO;
import com.example.demo.DTO.SpecialtyDTO;
import com.example.demo.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Obtener todos los doctores
    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    // Obtener un doctor por ID
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        DoctorDTO doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }

    // Obtener la especialidad de un doctor por su ID
    @GetMapping("/{id}/specialty")
    public ResponseEntity<SpecialtyDTO> getDoctorSpecialty(@PathVariable Long id) {
        SpecialtyDTO specialty = doctorService.getDoctorBySpecialtyById(id);
        return ResponseEntity.ok(specialty);
    }

    // Crear un nuevo doctor
    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        DoctorDTO createdDoctor = doctorService.createDoctor(doctorDTO);
        return ResponseEntity.ok(createdDoctor);
    }
}

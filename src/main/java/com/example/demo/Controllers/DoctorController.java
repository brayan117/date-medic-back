package com.example.demo.Controllers;

import com.example.demo.DTO.DoctorDTO;
import com.example.demo.DTO.SpecialtyDTO;
import com.example.demo.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Método auxiliar para invocar métodos privados usando reflexión
    @SuppressWarnings("unchecked")
    private <T> T invokePrivateMethod(String methodName, Class<?>[] paramTypes, Object... args) {
        try {
            Method method = DoctorService.class.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            return (T) method.invoke(doctorService, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error al invocar el método privado: " + methodName, e);
        }
    }

    // Obtener todos los doctores
    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctors = invokePrivateMethod("getAllDoctors", new Class<?>[]{});
        return ResponseEntity.ok(doctors);
    }

    // Obtener un doctor por ID
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        DoctorDTO doctor = invokePrivateMethod("getDoctorById", new Class<?>[]{Long.class}, id);
        return ResponseEntity.ok(doctor);
    }

    // Obtener la especialidad de un doctor por su ID
    @GetMapping("/{id}/specialty")
    public ResponseEntity<SpecialtyDTO> getDoctorSpecialty(@PathVariable Long id) {
        SpecialtyDTO specialty = invokePrivateMethod("getDoctorBySpecialtyById", new Class<?>[]{Long.class}, id);
        return ResponseEntity.ok(specialty);
    }

    // Crear un nuevo doctor
    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        DoctorDTO createdDoctor = invokePrivateMethod(
            "createDoctor", 
            new Class<?>[]{DoctorDTO.class}, 
            doctorDTO
        );
        return ResponseEntity.ok(createdDoctor);
    }
}

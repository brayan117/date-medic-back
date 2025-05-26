package com.example.demo.Controllers;

import com.example.demo.DTO.PatientDTO;
import com.example.demo.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Método auxiliar para invocar métodos privados usando reflexión
    @SuppressWarnings("unchecked")
    private <T> T invokePrivateMethod(String methodName, Class<?>[] paramTypes, Object... args) {
        try {
            Method method = PatientService.class.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            return (T) method.invoke(patientService, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error al invocar el método privado: " + methodName, e);
        }
    }

    // Obtener un paciente por ID
    @GetMapping("/{cc}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long cc) {
        PatientDTO patientDTO = invokePrivateMethod("getPatienById", new Class<?>[]{Long.class}, cc);
        return ResponseEntity.ok(patientDTO);
    }

    // Obtener todos los pacientes
    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = invokePrivateMethod("getAllPatients", new Class<?>[]{});
        return ResponseEntity.ok(patients);
    }

    // Crear un nuevo paciente
    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO createdPatient = invokePrivateMethod("createPatient", new Class<?>[]{PatientDTO.class}, patientDTO);
        return ResponseEntity.ok(createdPatient);
    }

    // Eliminar un paciente
    @DeleteMapping("/{cc}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long cc) {
        invokePrivateMethod("deletePatient", new Class<?>[]{Long.class}, cc);
        return ResponseEntity.noContent().build();
    }
}

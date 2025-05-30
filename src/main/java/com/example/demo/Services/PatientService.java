package com.example.demo.Services;


import com.example.demo.DTO.PatientDTO;
import com.example.demo.Entites.Patient;
import com.example.demo.Repositories.PatientRepository;
import com.example.demo.Util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private Converter converter;

    public PatientDTO getPatientById(Long cc){
        Patient patient = patientRepository.findById(cc).orElseThrow(()-> new RuntimeException("Paciente no encontrado"));
        return converter.convertToPatientDTO(patient);
    }

    public List<PatientDTO> getAllPatients(){
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(converter::convertToPatientDTO).collect(Collectors.toList());
    }

    @Transactional
    public PatientDTO createPatient(PatientDTO patientDTO) {
        // Validar campos requeridos
        if (patientDTO.name() == null || patientDTO.name().trim().isEmpty() ||
            patientDTO.lastName() == null || patientDTO.lastName().trim().isEmpty() ||
            patientDTO.email() == null || patientDTO.email().trim().isEmpty()) {
            throw new RuntimeException("Los campos nombre, apellido y email son obligatorios");
        }

        // Validar que el CC sea proporcionado
        if (patientDTO.cc() == null) {
            throw new RuntimeException("El número de cédula es obligatorio");
        }

        // Verificar si ya existe un paciente con ese CC
        if (patientRepository.findByCc(patientDTO.cc()).isPresent()) {
            throw new RuntimeException("Ya existe un paciente con la cédula: " + patientDTO.cc());
        }

        // Crear y guardar el nuevo paciente
        Patient patient = Patient.builder()
            .cc(patientDTO.cc())
            .name(patientDTO.name().trim())
            .lastName(patientDTO.lastName().trim())
            .gender(patientDTO.gender() != null ? patientDTO.gender().trim() : null)
            .email(patientDTO.email().trim())
            .phone(patientDTO.phone() != null ? patientDTO.phone().trim() : null)
            .build();

        try {
            Patient savedPatient = patientRepository.save(patient);
            return converter.convertToPatientDTO(savedPatient);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el paciente: " + e.getMessage());
        }
    }

    public void deletePatient(Long cc){
        if(!patientRepository.existsById(cc)){
            throw new RuntimeException("Paciente no encontrado");
        }else {
            patientRepository.deleteById(cc);
        }
    }

}

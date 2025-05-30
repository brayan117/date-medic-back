package com.example.demo.Services;


import com.example.demo.DTO.PatientDTO;
import com.example.demo.Entites.Patient;
import com.example.demo.Repositories.PatientRepository;
import com.example.demo.Util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public PatientDTO createPatient(PatientDTO patientDTO){
        Patient patient = new Patient();
        patient.setCc(patientDTO.cc());
        patient.setName(patientDTO.name());
        patient.setLastName(patientDTO.lastName());
        patient.setGender(patientDTO.gender());
        patient.setEmail(patientDTO.email());
        patient.setPhone(patientDTO.phone());

        return converter.convertToPatientDTO(patientRepository.save(patient));
    }

    public void deletePatient(Long cc){
        if(!patientRepository.existsById(cc)){
            throw new RuntimeException("Paciente no encontrado");
        }else {
            patientRepository.deleteById(cc);
        }
    }

}

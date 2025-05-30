package com.example.demo.Services;

import com.example.demo.DTO.HistoricalRecordDTO;
import com.example.demo.Entites.Doctor;
import com.example.demo.Entites.HistoricalRecord;
import com.example.demo.Entites.Patient;
import com.example.demo.Repositories.DoctorRepository;
import com.example.demo.Repositories.HrRepository;
import com.example.demo.Repositories.PatientRepository;
import com.example.demo.Util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HrService {
    @Autowired
    private HrRepository hrRepository;
    @Autowired
    private Converter converter;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    public HistoricalRecordDTO createHr(HistoricalRecordDTO hrDTO) {
        HistoricalRecord hr = new HistoricalRecord();
        hr.setDiagnosis(hrDTO.diagnosis());
        hr.setDescription(hrDTO.description());
        hr.setMedical_exam(hrDTO.medical_exam());
        hr.setPrescription(hrDTO.prescription());
        Patient patient = patientRepository.findById(hrDTO.ccPatient()).orElseThrow(()-> new RuntimeException("Paciente no encontrado"));
        hr.setPatient(patient);
        Doctor doctor = doctorRepository.findById(hrDTO.idDoctor()).orElseThrow(()-> new RuntimeException("Paciente no encontrado"));
        hr.setDoctor(doctor);
        return converter.convertToHCDTO(hrRepository.save(hr));
    }

    public HistoricalRecordDTO updateHr(Long id, HistoricalRecordDTO hrDTO){
        HistoricalRecord hr = hrRepository.findById(id).orElseThrow(()-> new RuntimeException("Historia clinica no encontrada"));
        hr.setDiagnosis(hrDTO.diagnosis());
        hr.setDescription(hrDTO.description());
        hr.setMedical_exam(hrDTO.medical_exam());
        hr.setPrescription(hrDTO.prescription());
        return converter.convertToHCDTO(hrRepository.save(hr));
    }

}

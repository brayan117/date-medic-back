package com.example.demo.Services;

import com.example.demo.DTO.DateDTO;
import com.example.demo.Entites.Date;
import com.example.demo.Entites.Doctor;
import com.example.demo.Entites.Patient;
import com.example.demo.Repositories.DateRepository;
import com.example.demo.Repositories.DoctorRepository;
import com.example.demo.Repositories.PatientRepository;
import com.example.demo.Util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DateService {

    @Autowired
    private DateRepository dateRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private Converter converter;

    public DateDTO createDate(DateDTO dateDTO){
        Date date = new Date();
        date.setDate(dateDTO.date());
        date.setHour(dateDTO.hour());
        Patient patiente = patientRepository.findById(dateDTO.ccPatient()).orElseThrow(()-> new RuntimeException("Paciente no encontrado"));
        date.setPatient(patiente);
        Doctor doctor = doctorRepository.findById(dateDTO.idDoctor()).orElseThrow(()-> new RuntimeException("Doctor no encontrado"));
        date.setDoctor(doctor);
        return converter.convertToDateDTO(dateRepository.save(date));
    }

    public DateDTO getDateById(Long id){
        Date date = dateRepository.findById(id).orElseThrow(()-> new RuntimeException("Cita no encontrada"));
        return converter.convertToDateDTO(date);
    }

    public DateDTO updateDate(Long id, DateDTO dateDTO){
        Date date = dateRepository.findById(id).orElseThrow(()-> new RuntimeException("Cita no encontrada"));
        date.setDate(dateDTO.date());
        date.setHour(dateDTO.hour());
        return converter.convertToDateDTO(dateRepository.save(date));
    }

}

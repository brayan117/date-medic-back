package com.example.demo.Services;


import com.example.demo.DTO.DateDTO;
import com.example.demo.DTO.DoctorDTO;
import com.example.demo.DTO.PatientDTO;
import com.example.demo.DTO.SpecialtyDTO;
import com.example.demo.Entites.Doctor;
import com.example.demo.Entites.Specialty;
import com.example.demo.Repositories.DoctorRepository;
import com.example.demo.Repositories.SpecialtyRepository;
import com.example.demo.Util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private SpecialtyRepository specialtyRepository;
    @Autowired
    private Converter converter;

    public List<DoctorDTO> getAllDoctors(){
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map(converter::converToDoctorDTO).collect(Collectors.toList());
    }

    public DoctorDTO getDoctorById(Long id){
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()-> new RuntimeException("Doctor no encontrado"));
        return converter.converToDoctorDTO(doctor);
    }

    public SpecialtyDTO getDoctorBySpecialtyById(Long id){
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()-> new RuntimeException("Doctor no encontrado"));
        Specialty specialty = doctor.getSpecialty();
        if (specialty != null){
            throw new RuntimeException("Especialidad no encontrada");
        }
        return converter.converToSpecialtyDTO(specialty);
    }

    public DoctorDTO createDoctor(DoctorDTO doctorDTO){
        Doctor doctor = new Doctor();
        doctor.setCc(doctor.getCc());
        doctor.setName(doctorDTO.name());
        doctor.setLastName(doctorDTO.lastName());
        doctor.setEmail(doctorDTO.email());
        doctor.setPhone(doctorDTO.phone());
        Specialty specialty = specialtyRepository.findById(doctorDTO.idSpecialty()).orElseThrow(()-> new RuntimeException("Especialidad no encontrada"));
        doctor.setSpecialty(specialty);
        return converter.converToDoctorDTO(doctorRepository.save(doctor));
    }




}

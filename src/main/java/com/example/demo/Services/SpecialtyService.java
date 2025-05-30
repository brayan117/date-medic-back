package com.example.demo.Services;

import com.example.demo.DTO.SpecialtyDTO;
import com.example.demo.Entites.Specialty;
import com.example.demo.Repositories.DoctorRepository;
import com.example.demo.Repositories.SpecialtyRepository;
import com.example.demo.Util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialtyService {

    @Autowired
    private Converter converter;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public List<SpecialtyDTO> findAll() {
        List<Specialty> specialties = specialtyRepository.findAll();
        return specialties.stream().map(converter::converToSpecialtyDTO).collect(Collectors.toList());
    }

    public SpecialtyDTO create(SpecialtyDTO specialtyDTO) {
        Specialty specialty = new Specialty();
        specialty.setName(specialtyDTO.name());

        return converter.converToSpecialtyDTO(specialtyRepository.save(specialty));
    }
}

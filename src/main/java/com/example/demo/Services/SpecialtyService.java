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
        if (specialtyDTO == null || specialtyDTO.name() == null || specialtyDTO.name().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la especialidad es requerido");
        }

        // Verificar si ya existe una especialidad con el mismo nombre
        if (specialtyRepository.existsByName(specialtyDTO.name())) {
            throw new IllegalArgumentException("Ya existe una especialidad con el nombre: " + specialtyDTO.name());
        }

        // Convertir DTO a entidad
        Specialty specialty = converter.convertToSpecialty(specialtyDTO);
        
        // Guardar la entidad
        Specialty savedSpecialty = specialtyRepository.save(specialty);
        
        // Convertir la entidad guardada de vuelta a DTO para la respuesta
        return converter.converToSpecialtyDTO(savedSpecialty);
    }
}

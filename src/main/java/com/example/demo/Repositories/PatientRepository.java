package com.example.demo.Repositories;

import com.example.demo.Entites.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Buscar paciente por número de cédula
    Optional<Patient> findByCc(Long cc);
}

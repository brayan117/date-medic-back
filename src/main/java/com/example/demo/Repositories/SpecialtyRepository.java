package com.example.demo.Repositories;

import com.example.demo.Entites.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    boolean existsByName(String name);
    Optional<Specialty> findByName(String name);
}

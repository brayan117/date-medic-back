package com.example.demo.Entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "specialty", schema = "clinica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Specialty {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;
    
    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @OneToMany(mappedBy = "specialty", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Doctor> doctors = new ArrayList<>();
    
    @PrePersist
    protected void onCreate() {
        // No es necesario hacer nada aquí, el ID se generará automáticamente
    }
    
    // Método helper para manejar la relación bidireccional
    public void addDoctor(Doctor doctor) {
        if (doctor != null) {
            doctors.add(doctor);
            doctor.setSpecialty(this);
        }
    }
    
    public void removeDoctor(Doctor doctor) {
        if (doctor != null) {
            doctors.remove(doctor);
            doctor.setSpecialty(null);
        }
    }
    
    @Override
    public String toString() {
        return "Specialty{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}

package com.example.demo.DTO;

import java.util.List;

public record PatientDTO(
    Long id,     // ID generado automáticamente
    Long cc,     // Cédula del paciente (obligatoria)
    String name,
    String lastName,
    String gender,
    String email,
    String phone,
    List<DateDTO> dates,
    List<HistoricalRecordDTO> historicalRecords
) {
    // Constructor sin ID para creación de nuevos pacientes
    public PatientDTO(Long cc, String name, String lastName, String gender, String email, String phone) {
        this(null, cc, name, lastName, gender, email, phone, null, null);
    }
    
    // Constructor con todos los campos
    public PatientDTO(Long id, Long cc, String name, String lastName, String gender, String email, String phone) {
        this(id, cc, name, lastName, gender, email, phone, null, null);
    }
}

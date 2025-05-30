package com.example.demo.Util;

import com.example.demo.DTO.*;
import com.example.demo.Entites.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Converter {
    // Métodos para Specialty

    public Specialty convertToSpecialty(SpecialtyDTO specialtyDTO) {
        if (specialtyDTO == null) {
            return null;
        }
        
        return Specialty.builder()
            .name(specialtyDTO.name())
            .build();
    }

    public Patient converToPartient(PatientDTO patientDTO) {
        if (patientDTO == null) {
            return null;
        }
        
        Patient patient = Patient.builder()
            .id(patientDTO.id())
            .cc(patientDTO.cc())
            .name(patientDTO.name())
            .lastName(patientDTO.lastName())
            .gender(patientDTO.gender())
            .email(patientDTO.email())
            .phone(patientDTO.phone())
            .build();
            
        // Convertir las fechas
        if (patientDTO.dates() != null) {
            List<Date> dates = patientDTO.dates().stream()
                .map(dateDTO -> {
                    Date date = converToDate(dateDTO);
                    date.setPatient(patient);
                    return date;
                })
                .collect(Collectors.toList());
            patient.setDates(dates);
        }
        
        // Convertir los registros históricos
        if (patientDTO.historicalRecords() != null) {
            List<HistoricalRecord> historicalRecords = patientDTO.historicalRecords().stream()
                .map(hrDTO -> {
                    HistoricalRecord hr = converToHC(hrDTO);
                    hr.setPatient(patient);
                    return hr;
                })
                .collect(Collectors.toList());
            patient.setHistoricalRecords(historicalRecords);
        }
        
        return patient;
    }

    public Date converToDate(DateDTO dateDTO){
        Date date = new Date();
        date.setId(dateDTO.id());
        date.setDate(dateDTO.date());
        date.setHour(dateDTO.hour());
        if (dateDTO.ccPatient() != null){
            Patient patient = new Patient();
            patient.setCc(dateDTO.ccPatient());
            date.setPatient(patient);
        }
        if (dateDTO.idDoctor() != null){
            Doctor doctor = new Doctor();
            doctor.setId(dateDTO.idDoctor());
            date.setDoctor(doctor);
        }
        if (dateDTO.idSecretariat() != null){
            Secretariat secretariat = new Secretariat();
            secretariat.setId(dateDTO.idSecretariat());
            date.setSecretariat(secretariat);
        }


        return date;
    }

    public Doctor converToDoctor(DoctorDTO doctorDTO){
        Doctor doctor = new Doctor();
        doctor.setId(doctorDTO.id());
        doctor.setCc(doctorDTO.cc());
        doctor.setName(doctorDTO.name());
        doctor.setLastName(doctorDTO.lastName());
        doctor.setEmail(doctorDTO.email());
        doctor.setPhone(doctorDTO.phone());
        List<HistoricalRecord> hcs = null;
        if (doctorDTO.historicalRecords() != null) {
            hcs = doctorDTO.historicalRecords().stream().map(hcDTO -> {;
                HistoricalRecord hc = converToHC(hcDTO);
                hc.setDoctor(doctor);
                return hc;
            }).collect(Collectors.toList());
        }
        doctor.setHistoricalRecords(hcs);
        List<Date> dates = null;
        if (doctorDTO.dates() != null) {
            dates = doctorDTO.dates().stream().map(dateDTO -> {
                Date date = converToDate(dateDTO);
                date.setDoctor(doctor);
                return date;
            }).collect(Collectors.toList());
        }
        doctor.setDates(dates);

        if (doctorDTO.idSpecialty() != null){
            Specialty specialty = new Specialty();
            specialty.setId(doctorDTO.idSpecialty());
            doctor.setSpecialty(specialty);
        }
        return doctor;
    }

    public HistoricalRecord converToHC(HistoricalRecordDTO hcDTO){
        HistoricalRecord hc = new HistoricalRecord();
        hc.setId(hcDTO.id());
        hc.setDiagnosis(hcDTO.diagnosis());
        hc.setDescription(hcDTO.description());
        hc.setMedical_exam(hcDTO.medical_exam());
        hc.setPrescription(hcDTO.prescription());
        if (hcDTO.ccPatient() != null){
            Patient patient = new Patient();
            patient.setCc(hcDTO.ccPatient());
            hc.setPatient(patient);
        }
        if (hcDTO.idDoctor() != null){
            Doctor doctor = new Doctor();
            doctor.setId(hcDTO.idDoctor());
            hc.setDoctor(doctor);
        }



        return hc;
    }



    public Secretariat convertToSecretariant(SecretariatDTO secDTO){
        Secretariat sec = new Secretariat();
        sec.setId(secDTO.id());
        sec.setCc(secDTO.cc());
        sec.setName(secDTO.name());
        sec.setLastName(secDTO.lastName());
        sec.setEmail(secDTO.email());
        List<Date> dates = null;
        if (secDTO.dates() != null){
            dates = secDTO.dates().stream().map(dateDTO -> {;
                Date date = converToDate(dateDTO);
                date.setSecretariat(sec);
                return date;
            }).collect(Collectors.toList());
        }
        sec.setDates(dates);

        return sec;
    }



    ////////////////////////////////////////////////////////////////////////////

    public PatientDTO convertToPatientDTO(Patient patient) {
        if (patient == null) {
            return null;
        }
        
        List<DateDTO> dateDTOs = null;
        if (patient.getDates() != null) {
            dateDTOs = patient.getDates().stream()
                .map(this::convertToDateDTO)
                .collect(Collectors.toList());
        }
        
        List<HistoricalRecordDTO> historicalRecordDTOs = null;
        if (patient.getHistoricalRecords() != null) {
            historicalRecordDTOs = patient.getHistoricalRecords().stream()
                .map(this::convertToHCDTO)
                .collect(Collectors.toList());
        }
        
        return new PatientDTO(
            patient.getId(),
            patient.getCc(),
            patient.getName(),
            patient.getLastName(),
            patient.getGender(),
            patient.getEmail(),
            patient.getPhone(),
            dateDTOs,
            historicalRecordDTOs
        );
    }

    public DateDTO convertToDateDTO(Date date){

        PatientDTO patientDTO = convertToPatientDTO(date.getPatient());
        DoctorDTO doctorDTO = converToDoctorDTO(date.getDoctor());
        SecretariatDTO secretariatDTO = converToSecretariantDTO(date.getSecretariat());

        return new DateDTO(
                date.getId(),
                date.getDate(),
                date.getHour(),
                patientDTO.cc(),
                doctorDTO.id(),
                secretariatDTO.id()
        );
    }

    public DoctorDTO converToDoctorDTO(Doctor doctor){

        List<HistoricalRecordDTO> hcDTO = doctor.getHistoricalRecords().stream().map(this::convertToHCDTO).collect(Collectors.toList());
        List<DateDTO> dateDTOS = doctor.getDates().stream().map(this::convertToDateDTO).collect(Collectors.toList());
        SpecialtyDTO specialtyDTO = converToSpecialtyDTO(doctor.getSpecialty());

        return new DoctorDTO(
                doctor.getId(),
                doctor.getCc(),
                doctor.getName(),
                doctor.getLastName(),
                doctor.getEmail(),
                doctor.getPhone(),
                hcDTO,
                dateDTOS,
                specialtyDTO.id()
        );
    }

    public HistoricalRecordDTO convertToHCDTO(HistoricalRecord hc){

        PatientDTO patientDTO = convertToPatientDTO(hc.getPatient());
        DoctorDTO doctorDTO = converToDoctorDTO(hc.getDoctor());

        return new HistoricalRecordDTO(
                hc.getId(),
                hc.getDiagnosis(),
                hc.getDescription(),
                hc.getMedical_exam(),
                hc.getPrescription(),
                patientDTO.cc(),
                doctorDTO.id()
        );
    }



    public SecretariatDTO converToSecretariantDTO(Secretariat secretariat){

        List<DateDTO> dateDTOS = secretariat.getDates().stream().map(this::convertToDateDTO).collect(Collectors.toList());

        return new SecretariatDTO(
                secretariat.getId(),
                secretariat.getCc(),
                secretariat.getName(),
                secretariat.getLastName(),
                secretariat.getEmail(),
                dateDTOS
        );
    }

    public SpecialtyDTO converToSpecialtyDTO(Specialty specialty){

        Set<DoctorDTO> doctorDTOS = specialty.getDoctors().stream().map(this::converToDoctorDTO).collect(Collectors.toSet());

        return new SpecialtyDTO(
                specialty.getId(),
                specialty.getName(),
                doctorDTOS
        );
    }

}


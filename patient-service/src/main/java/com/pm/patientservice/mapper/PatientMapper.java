package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequest;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toPatientResponseDTO(Patient patient) {
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(patient.getId().toString());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientResponseDTO;
    }

    public static Patient toPatient(PatientRequest patientResponseDTO) {
        Patient patient = new Patient();
        patient.setName(patientResponseDTO.getName());
        patient.setAddress(patientResponseDTO.getAddress());
        patient.setEmail(patientResponseDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientResponseDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientResponseDTO.getDateOfBirth()));
        return patient;
    }

}

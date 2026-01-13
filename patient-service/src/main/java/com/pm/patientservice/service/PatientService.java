package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequest;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j  // Lombok annotation for logging
public class PatientService {

    @Autowired
    private final PatientRepository patientRepository;


    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatient() {
        List<Patient> patientList = patientRepository.findAll();
        return patientList.stream().map(PatientMapper::toPatientResponseDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequest patientRequest) {
        Patient newPatient = patientRepository.save(PatientMapper.toPatient(patientRequest));
        log.debug("Patient entity after save: {}", newPatient);
        return PatientMapper.toPatientResponseDTO(newPatient);
    }
}

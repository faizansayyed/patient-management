package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequest;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exceptions.EmailAlreadyExistException;
import com.pm.patientservice.exceptions.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        if(patientRepository.existsByEmail(patientRequest.getEmail())) {
            throw new EmailAlreadyExistException("A patient with this email already exists "+patientRequest.getEmail());
        }
        Patient newPatient = patientRepository.save(PatientMapper.toPatient(patientRequest));
        log.debug("Patient entity after save: {}", newPatient);
        return PatientMapper.toPatientResponseDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequest patientRequest) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient with id "+id+" not found"));
        if(patientRepository.existByEmailAndIdNot(patientRequest.getEmail(), id)) {
            throw new EmailAlreadyExistException("A patient with this email already exists "+patientRequest.getEmail());
        }

        patient.setName(patientRequest.getName());
        patient.setEmail(patientRequest.getEmail());
        patient.setAddress(patientRequest.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequest.getDateOfBirth()));
        Patient updatedPatient  = patientRepository.save(patient);
        return PatientMapper.toPatientResponseDTO(updatedPatient);
    }


    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}

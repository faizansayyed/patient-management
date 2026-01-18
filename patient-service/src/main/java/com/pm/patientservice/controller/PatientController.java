package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequest;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.validators.CreatePatientValidationGroup;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for managing Patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patientResponseDTOList = patientService.getPatient();
        return ResponseEntity.ok().body(patientResponseDTOList);
    }

    @PostMapping
    @Operation(summary = "Create a new Patient")
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequest patientRequest) {
        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequest);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a new Patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @Validated(Default.class) @RequestBody PatientRequest patientRequest) {
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequest);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Patient")
    public ResponseEntity<PatientResponseDTO> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}

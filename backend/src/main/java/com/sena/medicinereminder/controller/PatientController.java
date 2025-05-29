package com.sena.medicinereminder.controller;

import com.sena.medicinereminder.DTO.PatientDTO;
import com.sena.medicinereminder.DTO.ResponseDTO;
import com.sena.medicinereminder.projection.PatientProjection;
import com.sena.medicinereminder.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PatientProjection>> getPatients() {
        List<PatientProjection> res = patientService.getAllPatients();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> addPatient(@RequestBody PatientDTO patientDTO) {
        ResponseDTO res = patientService.addPatient(patientDTO);
        return ResponseEntity.ok(res);
    }
}

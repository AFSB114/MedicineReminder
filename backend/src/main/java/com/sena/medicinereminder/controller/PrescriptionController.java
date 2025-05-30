package com.sena.medicinereminder.controller;

import com.sena.medicinereminder.DTO.PrescriptionDTO;
import com.sena.medicinereminder.DTO.ResponseDTO;
import com.sena.medicinereminder.model.Prescription;
import com.sena.medicinereminder.service.PrescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prescription")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Prescription>> getPrescriptions() {
        List<Prescription> res = prescriptionService.getPrescriptions();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> addPrescription(@RequestBody PrescriptionDTO prescriptionDTO) {
        ResponseDTO res = prescriptionService.addPrescription(prescriptionDTO);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/suspended/{id}")
    public ResponseEntity<ResponseDTO> suspendedPrescription(@PathVariable Long id) {
        ResponseDTO res = prescriptionService.suspendedPrescription(id);
        return ResponseEntity.ok(res);
    }
}

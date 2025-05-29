package com.sena.medicinereminder.service;

import com.sena.medicinereminder.DTO.PrescriptionDTO;
import com.sena.medicinereminder.DTO.ResponseDTO;
import com.sena.medicinereminder.model.Prescription;
import com.sena.medicinereminder.repository.IPrescription;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    private final IPrescription iPrescription;

    public PrescriptionService(IPrescription iPrescription) {
        this.iPrescription = iPrescription;
    }

    public List<Prescription> getPrescriptions() {
        return iPrescription.findAll();
    }

    public ResponseDTO addPrescription(PrescriptionDTO prescriptionDTO) {
        if (!validation(prescriptionDTO)) return ResponseDTO.error("All fields are required");

        Prescription prescription = DtoToModel(prescriptionDTO);
        iPrescription.save(prescription);

        return ResponseDTO.ok("Prescription added successfully");
    }

    public boolean validation(PrescriptionDTO prescriptionDTO) {
        return prescriptionDTO.getPatient() != null || prescriptionDTO.getMedicine() != null || prescriptionDTO.getDosage() != null;
    }

    public Prescription DtoToModel(PrescriptionDTO prescriptionDTO) {
        return new Prescription(
                prescriptionDTO.getPatient(),
                prescriptionDTO.getMedicine(),
                prescriptionDTO.getDosage()
        );
    }
}

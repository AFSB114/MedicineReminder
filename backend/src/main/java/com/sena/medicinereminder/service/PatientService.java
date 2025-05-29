package com.sena.medicinereminder.service;

import com.sena.medicinereminder.DTO.PatientDTO;
import com.sena.medicinereminder.DTO.ResponseDTO;
import com.sena.medicinereminder.model.Patient;
import com.sena.medicinereminder.projection.PatientProjection;
import com.sena.medicinereminder.repository.IPatient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final IPatient iPatient;

    public PatientService(IPatient iPatient) {
        this.iPatient = iPatient;
    }

    public ResponseDTO addPatient(PatientDTO patientDTO) {
        if (!validation(patientDTO)) return ResponseDTO.error("All fields are required");

        Patient patient = DtoToModel(patientDTO);
        iPatient.save(patient);

        return ResponseDTO.ok("Patient added successfully");
    }

    public List<PatientProjection> getAllPatients() {
        return iPatient.findAllPatients();
    }

    public Boolean validation(PatientDTO patientDTO) {
        return patientDTO.getFirstName() != null && patientDTO.getLastName() != null && patientDTO.getEmail() != null;
    }

    public Patient DtoToModel(PatientDTO patientDTO) {
        return new Patient(
                patientDTO.getFirstName(),
                patientDTO.getLastName(),
                patientDTO.getEmail()
        );
    }
}

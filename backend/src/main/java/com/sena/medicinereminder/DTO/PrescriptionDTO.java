package com.sena.medicinereminder.DTO;

import com.sena.medicinereminder.model.Medicine;
import com.sena.medicinereminder.model.Patient;

public class PrescriptionDTO {
    private Patient patient;
    private Medicine medicine;
    private Integer dosage;

    public PrescriptionDTO(Patient patient, Medicine medicine, Integer dosage) {
        this.patient = patient;
        this.medicine = medicine;
        this.dosage = dosage;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Integer getDosage() {
        return dosage;
    }

    public void setDosage(Integer dosage) {
        this.dosage = dosage;
    }
}

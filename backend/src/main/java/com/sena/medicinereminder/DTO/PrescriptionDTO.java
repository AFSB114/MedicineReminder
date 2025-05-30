package com.sena.medicinereminder.DTO;

import com.sena.medicinereminder.model.Medicine;
import com.sena.medicinereminder.model.Patient;

import java.time.LocalTime;
import java.util.List;

public class PrescriptionDTO {
    private Patient patient;
    private Medicine medicine;
    private Integer dosage;
    private List<LocalTime> times;

    public PrescriptionDTO(Patient patient, Medicine medicine, Integer dosage, List<LocalTime> times) {
        this.patient = patient;
        this.medicine = medicine;
        this.dosage = dosage;
        this.times = times;
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

    public List<LocalTime> getTimes() {
        return times;
    }

    public void setTimes(List<LocalTime> times) {
        this.times = times;
    }
}

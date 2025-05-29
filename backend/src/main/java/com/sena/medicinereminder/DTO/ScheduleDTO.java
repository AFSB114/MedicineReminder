package com.sena.medicinereminder.DTO;

import com.sena.medicinereminder.model.Prescription;

import java.time.LocalTime;

public class ScheduleDTO {
    private Prescription prescription;
    private LocalTime time;

    public ScheduleDTO(Prescription prescription, LocalTime time) {
        this.prescription = prescription;
        this.time = time;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}

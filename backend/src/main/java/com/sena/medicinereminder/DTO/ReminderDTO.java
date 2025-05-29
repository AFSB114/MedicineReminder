package com.sena.medicinereminder.DTO;

import com.sena.medicinereminder.model.Prescription;
import com.sena.medicinereminder.model.Schedule;

public class ReminderDTO {
    private Prescription prescription;
    private Schedule schedule;

    public ReminderDTO(Prescription prescription, Schedule schedule) {
        this.prescription = prescription;
        this.schedule = schedule;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}

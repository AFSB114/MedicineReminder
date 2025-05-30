package com.sena.medicinereminder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicine;

    @Column(name = "dosage", nullable = false)
    private Integer dosage;

    @Column(name = "suspended", nullable = false)
    private Boolean suspended = false;

    @Column(name = "suspended_date")
    private LocalDate suspendedDate;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reminder> reminderList;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Schedule> scheduleList;

    public Prescription() {
    }

    public Prescription(Patient patient, Medicine medicine, Integer dosage) {
        this.patient = patient;
        this.medicine = medicine;
        this.dosage = dosage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }

    public LocalDate getSuspendedDate(Object o) {
        return suspendedDate;
    }

    public void setSuspendedDate(LocalDate suspendedDate) {
        this.suspendedDate = suspendedDate;
    }

    public List<Reminder> getReminderList() {
        return reminderList;
    }

    public void setReminderList(List<Reminder> reminderList) {
        this.reminderList = reminderList;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
}

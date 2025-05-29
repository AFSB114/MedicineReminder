package com.sena.medicinereminder.model;

import com.sena.medicinereminder.definition.StatusReminder;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "reminder")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    private Prescription prescription;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @CreatedDate
    @Column(name = "remind_date", nullable = false)
    private LocalDate remindDate = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusReminder status = StatusReminder.PENDING;

    @Column(name = "sent_time")
    private LocalDateTime sentTime;

    @Column(name = "second_sent_time")
    private LocalDateTime secondSentTime;

    public Reminder() {
    }

    public Reminder(Prescription prescription, Schedule schedule) {
        this.prescription = prescription;
        this.schedule = schedule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(LocalDate remindDate) {
        this.remindDate = remindDate;
    }

    public StatusReminder getStatus() {
        return status;
    }

    public void setStatus(StatusReminder status) {
        this.status = status;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }

    public LocalDateTime getSecondSentTime() {
        return secondSentTime;
    }

    public void setSecondSentTime(LocalDateTime secondSentTime) {
        this.secondSentTime = secondSentTime;
    }
}

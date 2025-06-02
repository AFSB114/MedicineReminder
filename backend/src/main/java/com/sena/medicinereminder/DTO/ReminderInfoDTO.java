package com.sena.medicinereminder.DTO;

import com.sena.medicinereminder.definition.StatusReminder;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReminderInfoDTO {
    private Long id;
    private String FirstName;
    private String LastName;
    private String Email;
    private LocalTime scheduleTime;
    private String medicine;
    private LocalDateTime sentTime;
    private LocalDateTime secondSentTime;
    private StatusReminder status;

    public ReminderInfoDTO(Long id, String FirstName, String LastName, String Email,
                           LocalTime scheduleTime, String medicine,
                           LocalDateTime sentTime, LocalDateTime secondSentTime, StatusReminder status) {
        this.id = id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Email = Email;
        this.scheduleTime = scheduleTime;
        this.medicine = medicine;
        this.sentTime = sentTime;
        this.secondSentTime = secondSentTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public LocalTime getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(LocalTime scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
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

    public StatusReminder getStatus() {
        return status;
    }

    public void setStatus(StatusReminder status) {
        this.status = status;
    }
}

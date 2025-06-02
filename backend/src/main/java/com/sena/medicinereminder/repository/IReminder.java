package com.sena.medicinereminder.repository;

import com.sena.medicinereminder.DTO.ReminderInfoDTO;
import com.sena.medicinereminder.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface IReminder extends JpaRepository<Reminder, Long> {
    @Query("SELECT r FROM reminder r JOIN r.prescription p JOIN r.schedule s WHERE p.suspended = FALSE AND r.status = 'PENDING' AND s.time <= CURRENT_TIME")
    List<Reminder> remindersPending();

    @Query("SELECT r FROM reminder r WHERE r.status = 'SENT' AND r.sentTime <= :threshold AND r.secondSentTime IS NULL")
    List<Reminder> remindersSentBeforeOneHour(LocalDateTime threshold);

    @Query("SELECT r FROM reminder r WHERE r.status = 'SENT' AND r.secondSentTime <= :threshold")
    List<Reminder> remindersSecondSentBeforeOneHour(LocalDateTime threshold);

    @Query("""
    SELECT new com.sena.medicinereminder.DTO.ReminderInfoDTO(
        r.id,
        p.firstName,
        p.lastName,
        p.email,
        s.time,
        m.name,
        r.sentTime,
        r.secondSentTime,
        r.status
    )
    FROM reminder r
    JOIN r.prescription pr
    JOIN pr.patient p
    JOIN pr.medicine m
    JOIN r.schedule s
    """)
    List<ReminderInfoDTO> findAllReminderInfo();

}

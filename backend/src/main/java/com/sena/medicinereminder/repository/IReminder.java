package com.sena.medicinereminder.repository;

import com.sena.medicinereminder.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReminder extends JpaRepository<Reminder, Long> {
}

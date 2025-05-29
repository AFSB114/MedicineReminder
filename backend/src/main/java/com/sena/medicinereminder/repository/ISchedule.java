package com.sena.medicinereminder.repository;

import com.sena.medicinereminder.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISchedule extends JpaRepository<Schedule, Long> {
}

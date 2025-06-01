package com.sena.medicinereminder.repository;

import com.sena.medicinereminder.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISchedule extends JpaRepository<Schedule, Long> {
    @Query("SELECT s FROM schedule s WHERE prescription = :prescriptionId")
    List<Schedule> getSchedulesByPrescription(Long prescriptionID);
}

package com.sena.medicinereminder.repository;

import com.sena.medicinereminder.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPrescription extends JpaRepository<Prescription, Long> {
    @Query("SELECT p FROM prescription p WHERE p.suspended = TRUE AND p.suspendedDate < CURRENT_DATE")
    List<Prescription> prescriptionsSuspendedExpired();
}

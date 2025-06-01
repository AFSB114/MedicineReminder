package com.sena.medicinereminder.repository;

import com.sena.medicinereminder.model.Medicine;
import com.sena.medicinereminder.model.Patient;
import com.sena.medicinereminder.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPrescription extends JpaRepository<Prescription, Long> {
    @Query("SELECT p FROM prescription p WHERE p.suspended = TRUE AND p.suspendedDate < CURRENT_DATE")
    List<Prescription> prescriptionsSuspendedExpired();

    @Query("SELECT p.id FROM prescription p WHERE p.patient = :patient AND p.medicine = :medicine AND p.dosage = :dosage")
    Optional<Long> alreadyExistPatientAndMedicine(Patient patient, Medicine medicine, int dosage);
}

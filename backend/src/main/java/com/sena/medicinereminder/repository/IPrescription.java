package com.sena.medicinereminder.repository;

import com.sena.medicinereminder.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPrescription extends JpaRepository<Prescription, Long> {
}

package com.sena.medicinereminder.repository;

import com.sena.medicinereminder.model.Patient;
import com.sena.medicinereminder.projection.PatientProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPatient extends JpaRepository<Patient, Long> {
    @Query("SELECT p FROM patient p")
    List<PatientProjection> findAllPatients();
}

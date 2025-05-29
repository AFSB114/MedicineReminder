package com.sena.medicinereminder.repository;

import com.sena.medicinereminder.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicine extends JpaRepository<Medicine, Long> {
}

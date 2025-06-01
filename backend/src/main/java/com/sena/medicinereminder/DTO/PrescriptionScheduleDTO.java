package com.sena.medicinereminder.DTO;

import com.sena.medicinereminder.model.Medicine;
import com.sena.medicinereminder.model.Patient;

import java.time.LocalDate;

public class PrescriptionScheduleDTO {
    private Long id;
    private Patient patient;
    private Medicine medicine;
    private Integer dosage;
    private Boolean suspended;
    private LocalDate suspendedDate;
}

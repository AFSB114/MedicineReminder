package com.sena.medicinereminder.service;

import com.sena.medicinereminder.DTO.PrescriptionDTO;
import com.sena.medicinereminder.DTO.ResponseDTO;
import com.sena.medicinereminder.DTO.ScheduleDTO;
import com.sena.medicinereminder.model.Prescription;
import com.sena.medicinereminder.repository.IPrescription;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    private final IPrescription iPrescription;
    private final ScheduleService scheduleService;

    public PrescriptionService(IPrescription iPrescription, ScheduleService scheduleService) {
        this.iPrescription = iPrescription;
        this.scheduleService = scheduleService;
    }

    public List<Prescription> getPrescriptions() {
        return iPrescription.findAll();
    }

    public ResponseDTO addPrescription(PrescriptionDTO prescriptionDTO) {
        if (!validation(prescriptionDTO)) return ResponseDTO.error("All fields are required");

        Prescription prescription = DtoToModel(prescriptionDTO);
        Optional<Long> prescriptionOptional = iPrescription.alreadyExistPatientAndMedicine(prescription.getPatient(), prescription.getMedicine(), prescription.getDosage());
        if (prescriptionOptional.isEmpty()) {
            iPrescription.save(prescription);
        } else {
            prescription.setId(prescriptionOptional.get());
        }

        for (LocalTime time : prescriptionDTO.getTimes()) {
            ScheduleDTO scheduleDTO = new ScheduleDTO(prescription, time);
            scheduleService.addSchedule(scheduleDTO);
        }

        return ResponseDTO.ok("Prescription added successfully");
    }

    public ResponseDTO suspendedPrescription(Long id) {
        Optional<Prescription> prescriptionOptional = iPrescription.findById(id);
        if (prescriptionOptional.isEmpty()) return ResponseDTO.error("Prescription not found");

        Prescription prescription = prescriptionOptional.get();

        prescription.setSuspended(true);
        prescription.setSuspendedDate(LocalDate.now());
        iPrescription.save(prescription);

        return ResponseDTO.ok("Prescription suspended successfully");
    }

    public boolean validation(PrescriptionDTO prescriptionDTO) {
        return prescriptionDTO.getPatient() != null || prescriptionDTO.getMedicine() != null || prescriptionDTO.getDosage() != null;
    }

    public Prescription DtoToModel(PrescriptionDTO prescriptionDTO) {
        return new Prescription(
                prescriptionDTO.getPatient(),
                prescriptionDTO.getMedicine(),
                prescriptionDTO.getDosage()
        );
    }
}

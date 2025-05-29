package com.sena.medicinereminder.service;

import com.sena.medicinereminder.DTO.MedicineDTO;
import com.sena.medicinereminder.DTO.ResponseDTO;
import com.sena.medicinereminder.model.Medicine;
import com.sena.medicinereminder.repository.IMedicine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final IMedicine iMedicine;

    public MedicineService(IMedicine iMedicine) {
        this.iMedicine = iMedicine;
    }

    public List<Medicine> getAllMedicines() {
        return iMedicine.findAll();
    }

    public ResponseDTO addMedicine(MedicineDTO medicineDTO) {
        if (!validation(medicineDTO)) return ResponseDTO.error("You must enter a name");

        Medicine medicine = DtoToModel(medicineDTO);
        iMedicine.save(medicine);

        return ResponseDTO.ok("Medicine added successfully");
    }

    public boolean validation(MedicineDTO medicineDTO) {
        return medicineDTO.getName() != null && !medicineDTO.getName().isEmpty();
    }

    public Medicine DtoToModel(MedicineDTO medicineDTO) {
        return new Medicine(
                medicineDTO.getName(),
                medicineDTO.getDescription()
        );
    }
}

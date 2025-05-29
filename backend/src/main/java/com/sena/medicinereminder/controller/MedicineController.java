package com.sena.medicinereminder.controller;

import com.sena.medicinereminder.DTO.MedicineDTO;
import com.sena.medicinereminder.DTO.ResponseDTO;
import com.sena.medicinereminder.model.Medicine;
import com.sena.medicinereminder.service.MedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicine")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Medicine>> getMedicines() {
        List<Medicine> res = medicineService.getAllMedicines();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/")
    public ResponseEntity<?> addMedicine(@RequestBody MedicineDTO medicineDTO) {
        ResponseDTO res = medicineService.addMedicine(medicineDTO);
        return ResponseEntity.ok(res);
    }
}

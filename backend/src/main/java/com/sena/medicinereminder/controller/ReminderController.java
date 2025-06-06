package com.sena.medicinereminder.controller;

import com.sena.medicinereminder.DTO.ReminderDTO;
import com.sena.medicinereminder.DTO.ReminderInfoDTO;
import com.sena.medicinereminder.DTO.ResponseDTO;
import com.sena.medicinereminder.model.Reminder;
import com.sena.medicinereminder.service.ReminderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reminder")
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Reminder>> getAllReminders() {
        List<Reminder> res = reminderService.getAllReminders();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/info")
    public ResponseEntity<List<ReminderInfoDTO>> getInfoReminder() {
        List<ReminderInfoDTO> res = reminderService.getAllReminderInfo();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> addReminder(@RequestBody ReminderDTO reminderDTO) {
        ResponseDTO res = reminderService.addReminder(reminderDTO);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/confirm/{id}")
    public ResponseEntity<ResponseDTO> confirmReminder(@PathVariable Long id) {
        ResponseDTO res = reminderService.confirmReminder(id);
        return ResponseEntity.ok(res);
    }
}

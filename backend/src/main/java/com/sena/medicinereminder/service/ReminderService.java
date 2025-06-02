package com.sena.medicinereminder.service;

import com.sena.medicinereminder.DTO.ReminderDTO;
import com.sena.medicinereminder.DTO.ReminderInfoDTO;
import com.sena.medicinereminder.DTO.ResponseDTO;
import com.sena.medicinereminder.definition.StatusReminder;
import com.sena.medicinereminder.model.Reminder;
import com.sena.medicinereminder.repository.IReminder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {

    private final IReminder iReminder;

    public ReminderService(IReminder iReminder) {
        this.iReminder = iReminder;
    }

    public List<Reminder> getAllReminders() {
        return iReminder.findAll();
    }

    public List<ReminderInfoDTO> getAllReminderInfo() {
        return iReminder.findAllReminderInfo();
    }

    public ResponseDTO addReminder(ReminderDTO reminderDTO) {
        if(!validation(reminderDTO)) return ResponseDTO.error("All fields are required");

        Reminder reminder = DtoToModel(reminderDTO);
        iReminder.save(reminder);

        return ResponseDTO.ok("Reminder added successfully");
    }

    public ResponseDTO confirmReminder(Long id) {
        Optional<Reminder> reminderOptional = iReminder.findById(id);
        if(reminderOptional.isEmpty()) return ResponseDTO.error("Reminder not found");

        Reminder reminder = reminderOptional.get();
        reminder.setStatus(StatusReminder.CONFIRMED);
        iReminder.save(reminder);

        return ResponseDTO.ok("Reminder confirmed successfully");
    }

    public boolean validation(ReminderDTO reminderDTO) {
        return reminderDTO.getPrescription() != null && reminderDTO.getPrescription().getId() != null;
    }

    public Reminder DtoToModel(ReminderDTO reminderDTO) {
        return new Reminder(
                reminderDTO.getPrescription(),
                reminderDTO.getSchedule()
        );
    }
}

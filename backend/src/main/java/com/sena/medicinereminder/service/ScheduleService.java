package com.sena.medicinereminder.service;

import com.sena.medicinereminder.DTO.ReminderDTO;
import com.sena.medicinereminder.DTO.ResponseDTO;
import com.sena.medicinereminder.DTO.ScheduleDTO;
import com.sena.medicinereminder.model.Schedule;
import com.sena.medicinereminder.repository.ISchedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ISchedule iSchedule;
    private final ReminderService reminderService;

    public ScheduleService(ISchedule iSchedule, ReminderService reminderService) {
        this.iSchedule = iSchedule;
        this.reminderService = reminderService;
    }

    public List<Schedule> getAllSchedules() {
        return iSchedule.findAll();
    }

    public ResponseDTO addSchedule(ScheduleDTO scheduleDTO) {
        if (!validation(scheduleDTO)) return ResponseDTO.error("All fields are required");

        Schedule schedule = DtoToModel(scheduleDTO);
        iSchedule.save(schedule);

        reminderService.addReminder(new ReminderDTO(schedule.getPrescription(), schedule));

        return ResponseDTO.ok("Schedule added successfully");
    }

    public boolean validation(ScheduleDTO scheduleDTO) {
        return scheduleDTO.getPrescription() != null && scheduleDTO.getPrescription().getId() != null;
    }

    public Schedule DtoToModel(ScheduleDTO scheduleDTO) {
        return new Schedule(
                scheduleDTO.getPrescription(),
                scheduleDTO.getTime()
        );
    }
}

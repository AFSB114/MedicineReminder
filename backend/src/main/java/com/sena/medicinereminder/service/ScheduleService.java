package com.sena.medicinereminder.service;

import com.sena.medicinereminder.DTO.ResponseDTO;
import com.sena.medicinereminder.DTO.ScheduleDTO;
import com.sena.medicinereminder.model.Schedule;
import com.sena.medicinereminder.repository.ISchedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ISchedule iSchedule;

    public ScheduleService(ISchedule iSchedule) {
        this.iSchedule = iSchedule;
    }

    public List<Schedule> getAllSchedules() {
        return iSchedule.findAll();
    }

    public ResponseDTO addSchedule(ScheduleDTO scheduleDTO) {
        if (!validation(scheduleDTO)) return ResponseDTO.error("All fields are required");

        Schedule schedule = DtoToModel(scheduleDTO);
        iSchedule.save(schedule);

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

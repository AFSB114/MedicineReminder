package com.sena.medicinereminder.service;

import com.sena.medicinereminder.repository.ISchedule;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private final ISchedule iSchedule;

    public ScheduleService(ISchedule iSchedule) {
        this.iSchedule = iSchedule;
    }
}

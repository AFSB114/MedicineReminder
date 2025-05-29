package com.sena.medicinereminder.service;

import com.sena.medicinereminder.repository.IReminder;
import org.springframework.stereotype.Service;

@Service
public class ReminderService {

    private final IReminder iReminder;

    public ReminderService(IReminder iReminder) {
        this.iReminder = iReminder;
    }
}

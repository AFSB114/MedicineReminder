package com.sena.medicinereminder.task;

import com.sena.medicinereminder.definition.StatusReminder;
import com.sena.medicinereminder.model.Reminder;
import com.sena.medicinereminder.repository.IReminder;
import com.sena.medicinereminder.service.MailService;
import com.sena.medicinereminder.service.ReminderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class CheckReminderTask {

    private final ReminderService reminderService;
    private final IReminder iReminder;
    private final MailService mailService;

    public CheckReminderTask(ReminderService reminderService, IReminder iReminder, MailService mailService) {
        this.reminderService = reminderService;
        this.iReminder = iReminder;
        this.mailService = mailService;
    }

    @Scheduled(fixedRate = 60000)
    public void checkReminder() {
        List<Reminder> reminders = reminderService.getAllReminders();

        LocalTime now = LocalTime.now();

        for (Reminder reminder : reminders) {
            if (reminder.getStatus() == StatusReminder.SENT) return;
            if(reminder.getSchedule().getTime().isBefore(now)) {
                System.out.println("Checking reminder to schedule " + reminder.getId());
                reminder.setSentTime(LocalDateTime.now());
                reminder.setStatus(StatusReminder.SENT);
                mailService.sendReminderMail(reminder.getPrescription());
                iReminder.save(reminder);
                System.out.println("Status " + reminder.getStatus() + "  to reminder schedule " + reminder.getId());
            }
        }
    }
}

package com.sena.medicinereminder.task;

import com.sena.medicinereminder.DTO.ReminderDTO;
import com.sena.medicinereminder.definition.StatusReminder;
import com.sena.medicinereminder.model.Prescription;
import com.sena.medicinereminder.model.Reminder;
import com.sena.medicinereminder.model.Schedule;
import com.sena.medicinereminder.repository.IPrescription;
import com.sena.medicinereminder.repository.IReminder;
import com.sena.medicinereminder.service.MailService;
import com.sena.medicinereminder.service.ReminderService;
import com.sena.medicinereminder.service.ScheduleService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CheckReminderTask {

    private final IReminder iReminder;
    private final MailService mailService;
    private final IPrescription iPrescription;
    private final ScheduleService scheduleService;
    private final ReminderService reminderService;

    public CheckReminderTask(IReminder iReminder, MailService mailService, IPrescription iPrescription, ScheduleService scheduleService, ReminderService reminderService) {
        this.iReminder = iReminder;
        this.mailService = mailService;
        this.iPrescription = iPrescription;
        this.scheduleService = scheduleService;
        this.reminderService = reminderService;
    }

    @Scheduled(fixedRate = 60000)
    public void checkReminder() {
        System.out.println("Checking Reminder");

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beforeOneHour = now.minusMinutes(5);

        List<Reminder> reminderListPending = iReminder.remindersPending();

        for (Reminder reminder : reminderListPending) {
            System.out.println("Enviando recordatorio inicial para " + reminder.getId());
            reminder.setSentTime(now);
            reminder.setStatus(StatusReminder.SENT);
            mailService.sendReminderMail(reminder.getPrescription(), reminder.getId());
            iReminder.save(reminder);
        }

        List<Prescription> prescriptionListSuspendedExpired = iPrescription.prescriptionsSuspendedExpired();

        for (Prescription prescription : prescriptionListSuspendedExpired) {
            prescription.setSuspended(false);
            prescription.getSuspendedDate(null);
            iPrescription.save(prescription);
        }

        List<Reminder> reminderListSentBeforeOneHour = iReminder.remindersSentBeforeOneHour(beforeOneHour);

        for (Reminder reminder : reminderListSentBeforeOneHour) {
            System.out.println("Reenviando recordatorio " + reminder.getId());
            reminder.setSecondSentTime(now);
            mailService.sendReminderMail(reminder.getPrescription(),reminder.getId());
            iReminder.save(reminder);
        }

        List<Reminder> reminderListSecondSentBeforeOneHour = iReminder.remindersSecondSentBeforeOneHour(beforeOneHour);

        for (Reminder reminder : reminderListSecondSentBeforeOneHour) {
            System.out.println("Marcando como NO CONFIRMADO: " + reminder.getId());
            reminder.setStatus(StatusReminder.NOT_CONFIRMED);
            iReminder.save(reminder);
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void scheduleReminders() {
        List<Schedule> scheduleList = scheduleService.getAllSchedules();

        for (Schedule schedule : scheduleList) {
            ReminderDTO reminderDTO = new ReminderDTO(schedule.getPrescription(), schedule);
            reminderService.addReminder(reminderDTO);
        }
    }

}

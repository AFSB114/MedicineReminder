package com.sena.medicinereminder.service;

import com.sena.medicinereminder.model.Prescription;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public MailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendReminderMail(Prescription prescription, Long id) {
        Context context = new Context();
        context.setVariable("firstName", prescription.getPatient().getFirstName());
        context.setVariable("lastName", prescription.getPatient().getLastName());
        context.setVariable("medicine", prescription.getMedicine().getName());
        context.setVariable("dosage", prescription.getDosage());
        context.setVariable("confirmUrl", "http://localhost:3000/confirm/" + id);
        context.setVariable("suspendUrl", "http://localhost:3000/suspend/" + prescription.getId());

        String content = templateEngine.process("reminderMail", context);

        System.out.println("Reminder mail: " + prescription.getPatient().getFirstName());

        sendMail(prescription.getPatient().getEmail(), "Recordatorio de medicina", content);
    }

    private void sendMail(String to, String subject, String content) {
        System.out.println("Sending mail to " + to);
        try{
            System.out.println("Sending mail subject: " + subject);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            System.out.println("Email sent");

            mailSender.send(mimeMessage);

            System.out.println("Email sent");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

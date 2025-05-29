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

    private JavaMailSender mailSender;
    private TemplateEngine templateEngine;

    public void sendReminderMail(Prescription prescription) {
        Context context = new Context();
        context.setVariable("firstName", prescription.getPatient().getFirstName());
        context.setVariable("lastName", prescription.getPatient().getLastName());
        context.setVariable("medicine", prescription.getMedicine().getName());
        context.setVariable("dosage", prescription.getDosage());

        String content = templateEngine.process("reminderMail", context);

        sendMail(prescription.getPatient().getEmail(), "Recordatorio de medicina", content);
    }

    private void sendMail(String to, String subject, String content) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

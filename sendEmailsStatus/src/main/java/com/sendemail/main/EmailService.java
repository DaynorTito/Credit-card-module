package com.sendemail.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendStatusEmail(String recipient, String subject, String messageText) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipient);
            email.setFrom("creditcardmodule@gmail.com");
            email.setSubject(subject);
            email.setText(messageText);
            mailSender.send(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

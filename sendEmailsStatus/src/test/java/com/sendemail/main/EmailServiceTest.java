package com.sendemail.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EmailServiceTest {
    @Mock
    private JavaMailSender mailSender;

    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        emailService = new EmailService(mailSender);
    }

    @Test
    public void testSendStatusEmail() {
        // Configura las propiedades del correo electrónico
        String recipient = "daynortito13@gmail.com";
        String subject = "Test Subject";
        String messageText = "This is a test message.";

        // Simula el envío de correo
        emailService.sendStatusEmail(recipient, subject, messageText);


    }
}



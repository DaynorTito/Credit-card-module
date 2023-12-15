package org.company.com.domain.services;

//import jakarta.mail.Session;

import org.company.com.domain.model.RequestCreditCardStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailNotificationServiceTest {

    private static final String MOCK_EMAIL_TO = "testsendemail929@gmail.com";

    private EmailNotificationService emailNotificationService;

    @BeforeEach
    void setUp() {
        emailNotificationService = new EmailNotificationService();
    }

    @Test
    void testSendEmailStatusApplicationApproved() {
        boolean isSend = emailNotificationService.sendEmailApplicationStatus(MOCK_EMAIL_TO, RequestCreditCardStatus.APPROVED);
        assertTrue(isSend);
    }

    @Test
    void testSendEmailDataCard() {
        LocalDateTime date = LocalDateTime.now().plusYears(4);
        boolean isSendDataCard = emailNotificationService.sendEmailDataCard(MOCK_EMAIL_TO, "Juan Perez", "1234567890", 7000, date);
        assertTrue(isSendDataCard);
    }

    @Test
    void testSendEmailMinimumPaymentNotification(){
        LocalDateTime deadline = LocalDateTime.now().plusDays(7);
        boolean isSendMinimumPaymentNotification = emailNotificationService.sendMinimumMaymentMail(MOCK_EMAIL_TO,"Juan Perez",1000.0,deadline);
        assertTrue(isSendMinimumPaymentNotification);
    }
}

package org.company.com.domain.services;


import org.company.com.domain.model.*;
import org.jala.com.domain.model.*;
import org.jala.university.domain.model.*;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Properties;

@Service
public class EmailNotificationService {

    private static final String USERNAME = "creditcardmodule@gmail.com";
    private static final String PASSWORD = "rpxhjvnaybrtqukw";


    private Session session;

    public EmailNotificationService() {
        load();
    }

    public void load() {
        Properties properties = new Properties();
        /**
         * SMTP Server Configuration for Gmail
         * smtp is a protocol for sending emails which is an application layer protocol
         * that uses TCP as a transport layer protocol
         */
        properties.put("mail.smtp.host", "smtp.gmail.com");
        /**
         * SMTP Server Configuration for Gmail
         */
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        /**
         * Determines whether the SMTP server needs to authenticate
         * the user before sending the email
         * true if the SMTP server needs authentication
         * false if the SMTP server does not need authentication
         */
        properties.setProperty("mail.smtp.starttls.enable", "true");
        /**
         * Configures the SMTP server port number to connect to
         * for sending emails
         */
        properties.put("mail.smtp.port", "587");
        /**
         * UserName of the account to be used to send emails
         * from the SMTP server
         */
        properties.put("mail.smtp.user", USERNAME);
        /**
         * TLSv1.2 is the most recent version of TLS
         * TLS is a protocol that provides privacy and data integrity between two communicating applications
         */
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        /**
         * This property set to true causes the transport to attempt to send
         * the message even if the input stream is not available
         */
        properties.put("mail.smtp.auth", "true");

        /**
         * Creates a new Session object by using the given Properties object
         * to get the property values it uses to create and manage
         * the Session
         */
        session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
    }

    /**
     * @param to   email address to send the email
     * @param body body of the email to send
     * @return true if the email was sent successfully, false otherwise
     */
    private boolean sendEmail(String to, String body) {

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Credit Card Application Status");
            message.setContent(body, "text/html; charset=utf-8");

            /**
             * Use Transport to send the message to the recipient
             * Transport is a static method that connects to the default 
             * SMTP server and sends the message
             */
            Transport.send(message);

            return true;
        } catch (Exception err) {
            System.out.println("Email not sent  " + err.getMessage());
            return false;
        }
    }

    /**
     * @param to                      email address to send the email
     * @param requestCreditCardStatus status of the request e.g. APPROVED, REJECTED
     * @return status of the email sent
     */
    public boolean sendEmailApplicationStatus(String to, RequestCreditCardStatus requestCreditCardStatus) {
        RequestStatusEmailsModel body = new RequestStatusEmailsModel("User", requestCreditCardStatus);
        return sendEmail(to, body.getMailHTML());
    }

    /**
     * @param to          email address to send the email
     * @param name        full name of the user
     * @param cardNumber  card number of the credit card
     * @param creditLimit credit limit of the credit card
     * @param expiryDate  expiry date of the credit card
     * @return status of the email sent
     */
    public boolean sendEmailDataCard(String to, String name, String cardNumber, double creditLimit, LocalDateTime expiryDate) {
        CardDataTemplateEmail body = new CardDataTemplateEmail(name, cardNumber, creditLimit, expiryDate);
        return sendEmail(to, body.getMessageHTML());
    }

    /**
     * @param to                    The recipient's email address.
     * @param name                  The name of the user to whom the notification is addressed.
     * @param minimumMonthlyPayment The amount of the required minimum monthly payment.
     * @param deadline              The deadline for making the minimum payment.
     * @return true if the email is successfully sent, false otherwise.
     */
    public boolean sendMinimumMaymentMail(String to, String name, double minimumMonthlyPayment, LocalDateTime deadline) {
        MinimumPaymentNotification message = new MinimumPaymentNotification(name, minimumMonthlyPayment, deadline);
        return sendEmail(to, message.getMessageHTML());
    }

    /**
     * @param to          Email address to send the email.
     * @param name        Full name of the user.
     * @param amount      Amount of the failed payment.
     * @param dateFailure Date of the payment failure.
     * @return Status of the email sent.
     */
    public boolean sendEmailAutomaticPaymentFailure(String to, String name, double amount, LocalDateTime dateFailure) {
        AutomaticPaymentFailureEmailTemplate body = new AutomaticPaymentFailureEmailTemplate(name, amount, dateFailure);
        return sendEmail(to, body.getMessageHTML());
    }
    
public boolean sendCodeVerification(String to, String code) {
        CodeVerificationEmailTemplate body = new CodeVerificationEmailTemplate(code);
        return sendEmail(to, body.getMessageHTML());
    }
    
}

package org.company.com.controllers;

import lombok.AllArgsConstructor;
import org.company.com.controllers.exceptions.ControllerException;
import org.company.com.controllers.exceptions.RequestNullException;
import org.company.com.domain.model.Controller;
import org.company.com.domain.services.EmailNotificationService;

import java.security.SecureRandom;

@AllArgsConstructor
public class SendCodeController implements Controller<String, String> {

    private final EmailNotificationService emailNotificationService;

    @Override
    public String execute(final String email) throws ControllerException {

        if (email.isEmpty()) throw new RequestNullException();

        final String code = genRandomCode();

        boolean isSendCode = emailNotificationService.sendCodeVerification(email, code);

        if (!isSendCode) return null;

        return code;
    }

    private String genRandomCode() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);
        return String.format("%05d", num);
    }
}

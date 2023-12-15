package org.company.com.controllers;


import lombok.AllArgsConstructor;
import org.company.com.controllers.exceptions.ControllerException;
import org.company.com.controllers.exceptions.RequestNullException;
import org.company.com.domain.model.RequestCreditCardStatus;
import org.company.com.domain.services.EmailNotificationService;
import org.company.com.domain.services.RequestCreditCardServiceImpl;
import org.company.com.domain.Response.ResponseRequestCreditCardStatus;
import org.company.com.domain.model.Controller;
import org.company.com.domain.model.RequestCreditCard;
import org.company.com.domain.model.RequestCreditCardAccount;
import org.company.com.domain.repository.RequestCreditCardAccountRepository;
import org.company.com.domain.repository.RequestCreditCardRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
public class RequestCreditCardController implements Controller<ResponseRequestCreditCardStatus, RequestCreditCardAccount> {
    private final RequestCreditCardAccountRepository requestCreditCardAccountRepository;
    private final RequestCreditCardRepository requestCreditCardRepository;
    private final RequestCreditCardServiceImpl requestCreditCardService;
    private final EmailNotificationService emailNotificationService;

    public ResponseRequestCreditCardStatus execute(RequestCreditCardAccount request) throws ControllerException {

        if (request == null) throw new RequestNullException();

        /**
         * Insert the request credit card account into the database
         */
        UUID creditCardAccountID = requestCreditCardAccountRepository.insertOne(request);

        /**
         * Insert the request credit card into the database
         */
        UUID idRequestCreditCard = UUID.randomUUID();
        LocalDateTime todayTime = LocalDateTime.now();
        RequestCreditCard requestCreditCard = new RequestCreditCard(idRequestCreditCard, creditCardAccountID.toString(), todayTime, todayTime, RequestCreditCardStatus.PENDING, "", "");
        UUID idRequestCreditCardInserted = requestCreditCardRepository.insertOne(requestCreditCard);

        /**
         * Check if the request was created successfully
         */
        ResponseRequestCreditCardStatus requestCreated = requestCreditCardService.checkRequestSent(idRequestCreditCardInserted);
        emailNotificationService.sendEmailApplicationStatus(request.getEmail(), requestCreditCard.getStatus());

        return requestCreated;

    }

}

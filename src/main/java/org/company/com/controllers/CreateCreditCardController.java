package org.company.com.controllers;

import lombok.AllArgsConstructor;
import org.company.com.controllers.exceptions.ControllerException;
import org.company.com.controllers.exceptions.RequestNullException;
import org.company.com.domain.Response.ResponseCreditCard;
import org.company.com.domain.model.Controller;
import org.company.com.domain.model.JobStabilityStatus;
import org.company.com.domain.services.EmailNotificationService;
import org.company.com.domain.services.creditCardServices.*;
import org.company.com.domain.model.CreditCardLimit;
import org.company.com.domain.model.RequestCreditCardAccount;
import org.company.com.controllers.Requests.DataCreateCreditCardRequest;
import org.company.com.domain.repository.CreditCardRepository;
import org.company.com.domain.repository.UserRepository;
import org.company.com.mock.User;

import java.util.UUID;

@AllArgsConstructor
public class CreateCreditCardController implements Controller<ResponseCreditCard, DataCreateCreditCardRequest> {
    private final EmailNotificationService emailNotificationService;
    private final CreditCardService creditCardService;
    private final CreditCardLimit creditCardLimit;
    private final CardNumberGenerator cardNumberGenerator;
    private final CvvGenerator cvvGenerator;
    private final ExpirationDateGenerator expirationDateGenerator;
    private final CreditCardRepository creditCardRepository;
    private final UserRepository userRepository;
    private final RequestCreditCardAccount requestCreditCard;


    @Override
    public ResponseCreditCard execute(DataCreateCreditCardRequest request) throws ControllerException {
        if (request == null) throw new RequestNullException();

        String income = request.getIncome();
        JobStabilityStatus stability = request.getJobStabilityStatus();
        String name = request.getName();
        String lastName = request.getLastName();
        UUID userId = request.getUserId();

        String cardNumber = String.valueOf(cardNumberGenerator.getCardNumber());
        double limit = creditCardLimit.calculateLimit(income, stability);
        CreditAccount creditAccount = new CreditAccount(cardNumber, limit, 0);

        User user = new User(userId, requestCreditCard.getId(), request.getEmail(), name, lastName);
        userRepository.insertOne(user);
        CreditCard creditCard = creditCardService.generateCreditCard(user.getId(), user, creditAccount, cardNumber, cvvGenerator.generateCvv(), expirationDateGenerator.dateGenerator());
        creditCardRepository.insertOne(creditCard);

        emailNotificationService.sendEmailDataCard(request.getEmail(), request.getName(), String.valueOf(creditCard.getCardNumber()), limit, creditCard.getExpirationDate());

        return new ResponseCreditCard(String.valueOf(creditCard.getCardNumber()), true);
    }
}

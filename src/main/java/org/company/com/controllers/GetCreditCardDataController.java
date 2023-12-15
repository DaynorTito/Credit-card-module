package org.company.com.controllers;

import lombok.AllArgsConstructor;
import org.company.com.controllers.exceptions.ControllerException;
import org.company.com.controllers.exceptions.RequestNullException;
import org.company.com.domain.Response.ResponseCreditCardData;
import org.company.com.domain.model.Controller;
import org.company.com.domain.services.creditCardServices.CreditCard;
import org.company.com.domain.repository.CreditCardRepository;

@AllArgsConstructor
public class GetCreditCardDataController implements Controller<ResponseCreditCardData, String> {

    CreditCardRepository creditCardRepository;

    @Override
    public ResponseCreditCardData execute(String cardNumber) throws ControllerException {
        if (cardNumber == null || cardNumber.isEmpty()) throw new RequestNullException();
        CreditCard creditCardFound = creditCardRepository.findByCardNumber(cardNumber).orElse(null);
        boolean status = creditCardFound != null;
        return new ResponseCreditCardData(creditCardFound, status);
    }
}

package org.company.com.domain.services;

import org.company.com.domain.Response.ResponseRequestCreditCardStatus;
import org.company.com.domain.model.RequestCreditCard;
import org.company.com.domain.repository.RequestCreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RequestCreditCardServiceImpl {

    final static String REQUEST_SENT_SUCCESSFULLY = "Request sent successfully";
    final static String REQUEST_SENT_UNSUCCESSFULLY = "Error sending request";


    private final RequestCreditCardRepository requestCreditCardRepository;


    @Autowired
    public RequestCreditCardServiceImpl(RequestCreditCardRepository requestCreditCardRepository) {
        this.requestCreditCardRepository = requestCreditCardRepository;
    }


    public ResponseRequestCreditCardStatus checkRequestSent(UUID id) {
        Optional<RequestCreditCard> requestCreditCard = requestCreditCardRepository.findById(id);

        if (requestCreditCard.isPresent())
            return new ResponseRequestCreditCardStatus(REQUEST_SENT_SUCCESSFULLY, true, true);

        return new ResponseRequestCreditCardStatus(REQUEST_SENT_UNSUCCESSFULLY, false, false);
    }
}

package org.company.com.domain.repository;

import org.company.com.mock.MockTableRequestCreditCard;
import org.company.com.domain.model.RequestCreditCard;

import java.util.Optional;
import java.util.UUID;

public class RequestCreditCardRepository {

    private final MockTableRequestCreditCard mockRequestCreditCardDB;

    public RequestCreditCardRepository(MockTableRequestCreditCard mockRequestCreditCardDB) {
        this.mockRequestCreditCardDB = mockRequestCreditCardDB;
    }

    public Optional<RequestCreditCard> findById(UUID id) {

        RequestCreditCard filterRequestCreditCard = mockRequestCreditCardDB.getCollection()
                .stream()
                .filter(requestCreditCard -> requestCreditCard.getId().equals(id))
                .findFirst()
                .orElse(null);

        return Optional.ofNullable(filterRequestCreditCard);

    }

    public UUID insertOne(RequestCreditCard entity) {
        return mockRequestCreditCardDB.insertOne(entity);
    }
}

package org.company.com.mock;

import lombok.Getter;
import org.company.com.domain.services.creditCardServices.CreditCard;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter

public class MockTableCreditCard implements MockTable<CreditCard> {


    private final List<CreditCard> collection = new ArrayList<>();

    public MockTableCreditCard() {
//        CreditCard creditCard = new CreditCard("", 0, null, null, null);

        UUID userId = UUID.randomUUID();
        UUID cardId = UUID.randomUUID();

        CreditCard creditCard = new CreditCard(userId, cardId, "123456789", 0, null, null, null);

        collection.add(creditCard);

    }
}
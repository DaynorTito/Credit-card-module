package org.company.com.mock;

import lombok.Getter;
import org.company.com.domain.model.RequestCreditCard;
import org.company.com.domain.model.RequestCreditCardStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class MockTableRequestCreditCard implements MockTable<RequestCreditCard>, InserterTable<RequestCreditCard> {

    private final List<RequestCreditCard> collection = new ArrayList<>();

    public MockTableRequestCreditCard() {
        RequestCreditCard requestCreditCard = new RequestCreditCard();
        requestCreditCard.setId(UUID.fromString("6888afcb-07e8-4c2e-a9a0-6df02618a6d8"));
        requestCreditCard.setClient("530ad9ef-0f64-4ca9-b4bf-f526fba75d1d");
        requestCreditCard.setStatus(RequestCreditCardStatus.PENDING);
        requestCreditCard.setCreatedAt(LocalDateTime.now());
        requestCreditCard.setUpdatedAt(LocalDateTime.now());
        requestCreditCard.setReason("The client has a good credit history");
        requestCreditCard.setCard("069ef55d-c6f5-45a8-a4a2-cd318bb954fa");
        collection.add(requestCreditCard);
    }

    public UUID insertOne(RequestCreditCard entity) {
        collection.add(entity);
        return entity.getId();
    }
}

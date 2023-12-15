package org.company.com.mock;

import lombok.Getter;
import org.company.com.domain.model.RequestCreditCardAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class MockTableRequestCreditCardAccount implements InserterTable<RequestCreditCardAccount>, MockTable<RequestCreditCardAccount> {


    private final List<RequestCreditCardAccount> collection = new ArrayList<>();

    @Override
    public UUID insertOne(RequestCreditCardAccount entity) {
        collection.add(entity);
        return entity.getId();
    }
}

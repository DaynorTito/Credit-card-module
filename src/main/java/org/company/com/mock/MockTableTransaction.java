package org.company.com.mock;

import lombok.Getter;
import org.company.com.domain.model.Transaction;
import org.company.com.domain.model.TransactionStatus;
import org.company.com.domain.model.TypePayment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class MockTableTransaction implements MockTable<Transaction>, InserterTable<Transaction> {

    private final List<Transaction> collection = new ArrayList<>();


    public MockTableTransaction() {
        seed();
    }

    @Override
    public UUID insertOne(Transaction entity) {
        collection.add(entity);
        return entity.getId();
    }

    private void seed() {

        final UUID commonId = UUID.fromString("069ef55d-c6f5-45a8-a4a2-cd318bb90000");

        Transaction transaction1 = new Transaction();
        transaction1.setId(UUID.randomUUID());
        transaction1.setAmount(100.00);
        transaction1.setCreditCard(commonId);
        transaction1.setStatus(TransactionStatus.PENDING);
        transaction1.setConcept("Payment of the credit card");
        transaction1.setType(TypePayment.EXPENSE);
        transaction1.setCreatedAt(LocalDateTime.now());

        Transaction transaction2 = new Transaction();
        transaction2.setId(UUID.randomUUID());
        transaction2.setAmount(75.50);
        transaction2.setCreditCard(commonId);
        transaction2.setStatus(TransactionStatus.APPROVED);
        transaction2.setConcept("Grocery shopping");
        transaction2.setType(TypePayment.EXPENSE);
        transaction2.setCreatedAt(LocalDateTime.now());

        Transaction transaction3 = new Transaction();
        transaction3.setId(UUID.randomUUID());
        transaction3.setAmount(200.00);
        transaction3.setCreditCard(UUID.randomUUID());
        transaction3.setStatus(TransactionStatus.PENDING);
        transaction3.setConcept("Rent payment");
        transaction3.setType(TypePayment.EXPENSE);
        transaction3.setCreatedAt(LocalDateTime.now());

        Transaction transaction4 = new Transaction();
        transaction4.setId(UUID.randomUUID());
        transaction4.setAmount(500.00);
        transaction4.setCreditCard(UUID.randomUUID());
        transaction4.setStatus(TransactionStatus.APPROVED);
        transaction4.setConcept("Salary deposit");
        transaction4.setType(TypePayment.INCOME);
        transaction4.setCreatedAt(LocalDateTime.now());

        Transaction transaction5 = new Transaction();
        transaction5.setId(UUID.randomUUID());
        transaction5.setAmount(50.00);
        transaction5.setCreditCard(UUID.randomUUID());
        transaction5.setStatus(TransactionStatus.PENDING);
        transaction5.setConcept("Online purchase");
        transaction5.setType(TypePayment.EXPENSE);
        transaction5.setCreatedAt(LocalDateTime.now());


        collection.add(transaction1);
        collection.add(transaction2);
        collection.add(transaction3);
        collection.add(transaction4);
        collection.add(transaction5);
    }
}

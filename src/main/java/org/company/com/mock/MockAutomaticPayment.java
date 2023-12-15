package org.company.com.mock;

import org.company.com.domain.model.payment.AutomaticPayment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MockAutomaticPayment {


    private final List<AutomaticPayment> automaticPayments = new ArrayList<>();

    public MockAutomaticPayment() {
        seed();
    }

    public UUID insertAutomaticPayment(AutomaticPayment entity) {
        automaticPayments.add(entity);
        return entity.getId();
    }

    public List<AutomaticPayment> getCollection() {
        return automaticPayments;
    }

    private void seed() {
//        AutomaticPayment payment1 = new AutomaticPayment();
//        payment1.setId(UUID.randomUUID());
//        payment1.setAmount(100.00);
//        payment1.setCreditCard(UUID.randomUUID());
//        payment1.setAccount(UUID.randomUUID());
//        payment1.setStatus(PaymentStatus.PENDING);
//        payment1.setRepeatMonthlyAt(LocalDate.now().plusMonths(1));
//        payment1.setCreatedAt(LocalDateTime.now());
//
//        AutomaticPayment payment2 = new AutomaticPayment();
//        payment2.setId(UUID.randomUUID());
//        payment2.setAmount(150.50);
//        payment2.setCreditCard(UUID.randomUUID());
//        payment2.setAccount(UUID.randomUUID());
//        payment2.setStatus(PaymentStatus.CANCELLED);
//        payment2.setRepeatMonthlyAt(LocalDate.now().plusMonths(1));
//        payment2.setCreatedAt(LocalDateTime.now());
//
//        AutomaticPayment payment3 = new AutomaticPayment();
//        payment3.setId(UUID.randomUUID());
//        payment3.setAmount(200.75);
//        payment3.setCreditCard(UUID.randomUUID());
//        payment3.setAccount(UUID.randomUUID());
//        payment3.setStatus(PaymentStatus.PENDING);
//        payment3.setRepeatMonthlyAt(LocalDate.now().plusMonths(1));
//        payment3.setCreatedAt(LocalDateTime.now());
//
//        AutomaticPayment payment4 = new AutomaticPayment();
//        payment4.setId(UUID.randomUUID());
//        payment4.setAmount(75.20);
//        payment4.setCreditCard(UUID.randomUUID());
//        payment4.setAccount(UUID.randomUUID());
//        payment4.setStatus(PaymentStatus.REJECTED);
//        payment4.setRepeatMonthlyAt(LocalDate.now().plusMonths(1));
//        payment4.setCreatedAt(LocalDateTime.now());
//
//        automaticPayments.add(payment1);
//        automaticPayments.add(payment2);
//        automaticPayments.add(payment3);
//        automaticPayments.add(payment4);
    }
}

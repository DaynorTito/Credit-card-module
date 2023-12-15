package org.company.com.mock;

import org.company.com.domain.model.payment.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MockTablePayment implements MockTable<Payment> {

    private final List<Payment> payments = new ArrayList<>();
    private final UUID idTest = UUID.fromString("069ef55d-c6f5-45a8-a4a2-cd318bb90001");

    public MockTablePayment() {
        seed();
    }

    @Override
    public List<Payment> getCollection() {
        return payments;
    }

    private void seed() {
//    AutomaticPayment payment1 = new AutomaticPayment();
//    payment1.setId(UUID.randomUUID());
//    payment1.setAmount(100.00);
//    payment1.setCreditCard(idTest);
//    payment1.setAccount(UUID.randomUUID());
//    payment1.setStatus(PaymentStatus.APPROVED);
//    payment1.setRepeatMonthlyAt(LocalDate.EPOCH);
//    payment1.setCreatedAt(LocalDateTime.now());
//
//    ManualPayment payment2 = new ManualPayment();
//    payment2.setId(UUID.randomUUID());
//    payment2.setAmount(100.00);
//    payment2.setCreditCard(idTest);
//    payment2.setAccount(UUID.randomUUID());
//    payment2.setStatus(PaymentStatus.APPROVED);
//    payment2.setCreatedAt(LocalDateTime.now());
    }

}

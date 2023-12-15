package org.company.com.domain.model.payment;

import java.time.LocalDateTime;
import java.util.UUID;

public interface Payment {
    UUID getId();

    double getAmount();

    UUID getCreditCard();

    UUID getAccount();

    PaymentStatus getStatus();

    LocalDateTime getCreatedAt();
}

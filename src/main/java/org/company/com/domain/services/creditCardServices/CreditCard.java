package org.company.com.domain.services.creditCardServices;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.company.com.mock.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CreditCard {
    private UUID userId;
    private UUID id;
    private String cardNumber;
    private int cvv;
    private LocalDateTime expirationDate;
    private User user;
    private CreditAccount creditAccount;
}
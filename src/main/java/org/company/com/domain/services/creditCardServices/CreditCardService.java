package org.company.com.domain.services.creditCardServices;

import lombok.NoArgsConstructor;
import org.company.com.mock.User;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
public class CreditCardService {

    public CreditCard generateCreditCard(UUID userId, User user, CreditAccount creditAccount, String cardNumber, int cvv, LocalDateTime expirationDate) {
        UUID cardId = UUID.randomUUID();
        CreditCard creditCard = new CreditCard(userId, cardId, cardNumber, cvv, expirationDate, user, creditAccount);
        return creditCard;
    }

}
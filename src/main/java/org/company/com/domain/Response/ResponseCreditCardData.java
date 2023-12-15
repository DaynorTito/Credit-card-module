package org.company.com.domain.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.company.com.domain.services.creditCardServices.CreditCard;

@Data
@AllArgsConstructor
public class ResponseCreditCardData {
    private final CreditCard creditCard;
    private final boolean status;
}

package org.company.com.domain.Response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseCreditCard {
    private final String cardNumber;
    private final boolean status;
}

package org.company.com.domain.services.creditCardServices;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditAccount {
    private String accountNumber;
    private double availableBalance;
    private double currentDebt;

}

package org.company.com.domain.services.creditCardServices;

import java.time.LocalDateTime;

public interface CreditCardInformation {
  double getCurrentBalance();
  LocalDateTime getDueDate();
  double getMinimumPayment();
  String getLastThreeCardDigits();

}

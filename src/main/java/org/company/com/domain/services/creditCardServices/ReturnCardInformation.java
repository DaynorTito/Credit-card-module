package org.company.com.domain.services.creditCardServices;

import java.time.LocalDateTime;

public class ReturnCardInformation implements CreditCardInformation {
  private CreditCard creditCard;

  public ReturnCardInformation(CreditCard creditCard) {
    this.creditCard = creditCard;
  }

  @Override
  public double getCurrentBalance() {
    return creditCard.getCreditAccount().getAvailableBalance();
  }

  @Override
  public LocalDateTime getDueDate() {
    return creditCard.getExpirationDate();
  }

  @Override
  public double getMinimumPayment() {
    double currentBalance= creditCard.getCreditAccount().getAvailableBalance();
    return currentBalance * 0.10;
  }

  @Override
  public String getLastThreeCardDigits() {
    String numberCard= String.valueOf(creditCard.getCardNumber());
    return numberCard.substring(numberCard.length() - 3);
  }
}

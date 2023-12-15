package org.company.com.domain.model;

public class CreditCardLimit {
  public CreditCardLimit() {
  }

  public double calculateLimit(String income, JobStabilityStatus stability) {
    double result;
    switch (stability){
      case NO -> {
        return 0;
      }
      //40% of Income
      case STABLE -> {
        result = Double.parseDouble(income) / 2.5;
        return Math.round(result * 100) / 100.0;
      }
      //20% of Income
      case TEMPORAL -> {
        result = Double.parseDouble(income) / 5;
        return Math.round(result * 100) / 100.0;
      }
      default -> {
        throw new RuntimeException("Illegal Argument Exception.");
      }
    }
  }
}

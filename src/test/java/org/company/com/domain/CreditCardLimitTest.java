package org.company.com.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.company.com.domain.model.JobStabilityStatus;
import org.company.com.domain.model.CreditCardLimit;
import org.junit.jupiter.api.Test;

public class CreditCardLimitTest {
  private CreditCardLimit creditCardLimit = new CreditCardLimit();

  @Test
  public void test100IncomeStable() {
    assertEquals(creditCardLimit.calculateLimit("100", JobStabilityStatus.STABLE), 40.00);
  }

  @Test
  public void test100IncomeTemporal() {
    assertEquals(creditCardLimit.calculateLimit("100", JobStabilityStatus.TEMPORAL), 20.00);
  }

  @Test
  public void test5000IncomeStable() {
    assertEquals(creditCardLimit.calculateLimit("5000", JobStabilityStatus.STABLE), 2000.00);
  }

  @Test
  public void test5000IncomeTemporal() {
    assertEquals(creditCardLimit.calculateLimit("5000", JobStabilityStatus.TEMPORAL), 1000.00);
  }
}

package org.company.com.domain.services.creditCardServicesTest;

import java.time.LocalDateTime;

import org.company.com.domain.services.creditCardServices.ExpirationDateGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpirationDateGeneratorTest {
  @Test
  public void testDateGenerator() {
    ExpirationDateGenerator expirationDateGenerator = new ExpirationDateGenerator();
    LocalDateTime currentDate = LocalDateTime.now();
    LocalDateTime expirationDate = expirationDateGenerator.dateGenerator();
    assertEquals(currentDate.plusYears(4).toLocalDate(), expirationDate.toLocalDate());
  }
}
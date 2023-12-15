package org.company.com.domain.services.creditCardServices;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExpirationDateGenerator {

  public LocalDateTime dateGenerator() {
    BigDecimal currentYear = BigDecimal.valueOf(LocalDateTime.now().getYear());
    BigDecimal currentMonth = BigDecimal.valueOf(LocalDateTime.now().getMonthValue());
    BigDecimal currentDay = BigDecimal.valueOf(LocalDateTime.now().getDayOfMonth());
    BigDecimal currentHour = BigDecimal.valueOf(LocalDateTime.now().getHour());
    BigDecimal currentMinute = BigDecimal.valueOf(LocalDateTime.now().getMinute());
    BigDecimal currentSecond = BigDecimal.valueOf(LocalDateTime.now().getSecond());

    BigDecimal expirationYear = currentYear.add(BigDecimal.valueOf(4));

    int year = expirationYear.setScale(0, BigDecimal.ROUND_DOWN).intValue();
    int month = currentMonth.setScale(0, BigDecimal.ROUND_DOWN).intValue();
    int day = currentDay.setScale(0, BigDecimal.ROUND_DOWN).intValue();
    int hour = currentHour.setScale(0, BigDecimal.ROUND_DOWN).intValue();
    int minute = currentMinute.setScale(0, BigDecimal.ROUND_DOWN).intValue();
    int second = currentSecond.setScale(0, BigDecimal.ROUND_DOWN).intValue();

    LocalDateTime expirationDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
    System.out.println(expirationDateTime);
    return expirationDateTime;
  }
}
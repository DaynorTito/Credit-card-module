package org.company.com.domain.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterestCalculatorTest {
    @Test
    public void testCalculateInterest() {
        InterestCalculator calculator = new InterestCalculator();
        double currentBalance = 1000.0;
        double expectedMonthlyInterest = 15.0;
        double actualMonthlyInterest = calculator.calculateInterest(currentBalance);
        assertEquals(expectedMonthlyInterest, actualMonthlyInterest, 0.01);
    }

    @Test
    public void testCalculateAccumulatedInterest() {
        InterestCalculator calculator = new InterestCalculator();
        double currentBalance = 1000.0;
        int monthsUnpaid = 3;
        double expectedAccumulatedInterest = 45.0;
        double actualAccumulatedInterest = calculator.calculateAccumulatedInterest(currentBalance, monthsUnpaid);
        assertEquals(expectedAccumulatedInterest, actualAccumulatedInterest, 0.01);
    }

    @Test
    public void testCalculateLatePaymentInterest() {
        InterestCalculator calculator = new InterestCalculator();
        double overdueBalance = 500.0;
        double expectedLatePaymentInterest = 75.0;
        double actualLatePaymentInterest = calculator.calculateLatePaymentInterest(overdueBalance);
        assertEquals(expectedLatePaymentInterest, actualLatePaymentInterest, 0.01);
    }

    @Test
    public void testCalculateTotalLateInterest() {
        InterestCalculator calculator = new InterestCalculator();
        double currentBalance = 1000.0;
        int monthsUnpaid = 2;
        double accumulatedInterest = calculator.calculateAccumulatedInterest(currentBalance, monthsUnpaid);
        double latePaymentInterest = calculator.calculateLatePaymentInterest(currentBalance);
        double expectedTotalLateInterest = accumulatedInterest + latePaymentInterest;
        double actualTotalLateInterest = calculator.calculateTotalLateInterest(currentBalance, monthsUnpaid);
        assertEquals(expectedTotalLateInterest, actualTotalLateInterest, 0.01);
    }
}
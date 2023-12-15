package org.company.com.domain.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinimumPaymentCalculatorTest {
    @Test
    public void testCalculateMinimumPayment() {
        MinimumPaymentCalculator calculator = new MinimumPaymentCalculator();
        double limitCredit = 1000.0;
        double expectedMinimumPayment = 50.0;
        double actualMinimumPayment = calculator.calculateMinimumPayment(limitCredit);
        assertEquals(expectedMinimumPayment, actualMinimumPayment, 0.01);
    }

    @Test
    public void testCalculateTotalPayment() {
        MinimumPaymentCalculator calculator = new MinimumPaymentCalculator();
        double limitCredit = 1000.0;
        double expectedTotalPayment = 60.0;
        double annualInterst = 5.0;
        expectedTotalPayment += annualInterst;
        double actualTotalPayment = calculator.calculateTotalPayment(limitCredit);
        assertEquals(expectedTotalPayment, actualTotalPayment, 0.01);
    }
}
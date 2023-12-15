package org.company.com.domain.services;

public class InterestCalculator {

    private static final double LATE_PAYMENT_INTEREST_RATE = 0.15;
    private static final double ANNUAL_PAYMENT_PERCENTAGE = 0.18;
    public InterestCalculator(){

    }
    /**
     * Calculate the monthly interest for a credit card based on the current balance and the annual interest rate.
     *
     * @param currentBalance     The current balance on the credit card.
     * @return The calculated monthly interest amount.
     */
    public double calculateInterest(double currentBalance) {
        return currentBalance * (ANNUAL_PAYMENT_PERCENTAGE / 12);
    }

    /**
     * Calculates the accumulated interest for a given current balance over a period of unpaid months.
     *
     * @param currentBalance       The current balance.
     * @param monthsUnpaid         The number of unpaid months.
     * @return                    The accumulated interest over the period.
     */
    public double calculateAccumulatedInterest(double currentBalance, int monthsUnpaid) {
        double monthlyInterest = calculateInterest(currentBalance);
        return monthlyInterest * monthsUnpaid;
    }


    /**
     * Calculate the late payment interest for a credit card based on the overdue balance.
     *
     * @param overdueBalance The overdue balance on the credit card.
     * @return The calculated late payment interest amount.
     */
    public double calculateLatePaymentInterest(double overdueBalance) {
        return overdueBalance * LATE_PAYMENT_INTEREST_RATE;
    }

    /**
     * Calculate the total late interest for a credit card based on the current balance and the number of unpaid months.
     *
     * @param currentBalance The current balance on the credit card.
     * @param monthsUnpaid   The number of unpaid months.
     * @return The total late interest amount.
     */
    public double calculateTotalLateInterest(double currentBalance, int monthsUnpaid) {
        double annualInterest = calculateAccumulatedInterest(currentBalance, monthsUnpaid);
        double latePaymentInterest = calculateLatePaymentInterest(currentBalance);
        return annualInterest + latePaymentInterest;
    }
}

package org.company.com.domain.services;

public class MinimumPaymentCalculator {

    private static final double MINIMUM_PAYMENT_RATE = 0.05;
    private InterestCalculator interestCalculator;

    public MinimumPaymentCalculator(){
        interestCalculator = new InterestCalculator();
    }

    /**
     * Calculate the minimum payment for a credit card based on the current balance and the minimum payment percentage.
     *
     * @param limitCredit           Assigned credit limit, total debt
     * @return The calculated minimum payment amount.
     */
    public double calculateMinimumPayment(double limitCredit) {
        return limitCredit * MINIMUM_PAYMENT_RATE;
    }

    /**
     * Calculate the total payment amount (minimum payment + interest) for a credit card.
     *
     * @param limitCredit           Assigned credit limit, total debt
     * @return The total payment amount including the minimum payment and interest.
     */
    public double calculateTotalPayment(double limitCredit) {
        double minimumPayment = calculateMinimumPayment(limitCredit);
        double annualInterest = interestCalculator.calculateInterest(limitCredit);
        return minimumPayment + annualInterest;
    }

}

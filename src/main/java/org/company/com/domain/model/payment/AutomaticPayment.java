package org.company.com.domain.model.payment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class AutomaticPayment {

    /**
     * The id of the payment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The amount of the payment.
     * Column precision = 2 because the amount is in dollars. e.g. 100.00
     * Column nullable = false because the amount is required.
     */
    @Column(nullable = false, precision = 2)
    private double amount;

    /**
     * The credit card id of the payment ( FK ).
     * Column nullable = false because the credit card id is required.
     * UUID because the credit card id is a UUID.
     */
    @Column(nullable = false)
    private UUID creditCard;

    /**
     * The account related to the automatic payment.
     * UUID to get the ID of the account.
     */
    @Column()
    private UUID account;

    /**
     * The status of the payment.
     */
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    /**
     * The date when the payment is automated.
     * Column nullable = false because the date is required.
     */
    @Column(nullable = false)
    private LocalDate repeatMonthlyAt;

    /**
     * The date when the payment was created.
     * Column nullable = false because the date is required.
     */
    @Column(nullable = false)
    private LocalDate createdAt;
}

package org.company.com.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Transaction {
    
    /**
     * The id of the transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The amount of the transaction.
     * Column precision = 2 because the amount is in dollars. e.g. 100.00
     * Column nullable = false because the amount is required.
     */
    @Column(nullable = false, precision = 2)
    private double amount;


    /**
     * The credit card id of the transaction ( FK ).
     * Column nullable = false because the credit card id is required.
     * UUID because the credit card id is a UUID.
     */
    @Column(nullable = false)
    private UUID creditCard;


    /**
     * The status of the transaction.
     */
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    /**
     * The concept of the transaction.
     * Column nullable = false because the concept is required.
     * e.g. "Payment of the credit card"
     */
    @Column(nullable = false)
    private String concept;

    /**
     * Type of transaction.
     * Column nullable = false because the type is required.
     * e.g. "Income" or "Expense"
     */
    @Enumerated(EnumType.STRING)
    private TypePayment type;

    /**
     * The date when the transaction was created.
     * Column nullable = false because the date is required.
     */
    @Column(nullable = false)
    private LocalDateTime createdAt;


}

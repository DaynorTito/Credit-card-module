package org.company.com.domain.model.payment;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
public class ManualPayment implements Payment{

    /**
     * The unique identifier for the manual payment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The amount of the manual payment.
     * Column precision = 2 because the amount is in dollars. e.g. 100.00
     * Column nullable = false because the amount is required.
     */
    @Column(nullable = false, precision = 2)
    private double amount;

    /**
     * The credit card identifier associated with the manual payment (foreign key).
     * Column nullable = false because the credit card identifier is required.
     * UUID because the credit card identifier is a UUID.
     */
    @Column(nullable = false)
    private UUID creditCard;

    /**
     * The account identifier related to the manual payment.
     * UUID to obtain the ID of the account.
     */
    @Column()
    private UUID account;

    /**
     * The status of the manual payment.
     */
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    /**
     * The date when the manual payment was created.
     * Column nullable = false because the date is required.
     */
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;
}

package org.company.com.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.company.com.domain.DAO.EntityDAO;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

//@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreditCard implements EntityDAO<UUID> {
    /**
     * The id of the request credit card.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The client if of the request credit card ( FK ).
     */
    @Column(length = 36)
    private String client;

    /**
     * The date when the request credit card was created.
     */
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    /**
     * The date when the request credit card was updated.
     * e.g.
     * - The request credit card was approved
     * - The request credit card was rejected
     * - The request credit card was canceled
     */
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * The status of the request credit card.
     */
    @Enumerated(EnumType.STRING)
    private RequestCreditCardStatus status;

    /**
     * The reason why the request credit card was rejected or canceled.
     * It is null when the request credit card was approved.
     */
    @Column(length = 256)
    private String reason;

    /**
     * The card id of the request credit card ( FK ).
     * It is null when the request credit card was rejected or canceled.
     */
    @Column(length = 36)
    private String card;
}

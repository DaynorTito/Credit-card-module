package org.company.com.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreditCardAccount {

    /*
     * The ID of the credit card application.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The first name of the credit card applicant.
     */
    @Column(length = 36)
    private String firstName;


    /**
     * The last name of the credit card applicant.
     */
    @Column(length = 36)
    private String lastName;

    /**
     * The birthdate of the credit card applicant.
     */
    @Column(nullable = false)
    private Date birthDate;

    /**
     * The address of the credit card applicant.
     */
    @Column(length = 256)
    private String address;

    /**
     * The phone number of the credit card applicant.
     */
    @Column(length = 32)
    private String dniNumber;

    /**
     * The email of the credit card applicant.
     */
    @Column(length = 256)
    private String email;

    /**
     * Current corp is the company where the credit card applicant works.
     */
    @Column(nullable = false, length = 256)
    private String currentCorp;

    /**
     * Current position is the position of the credit card applicant in the company.
     */
    @Column(nullable = false, length = 256)
    private String currentPosition;

    /*
     * Account holder is the credit card applicant has an account in the bank.
     */
    @Column(nullable = false)
    private Boolean accountHolder;

    /**
     * Not account holder is the credit card applicant does not have an account in the bank.
     */
    @Column(nullable = false)
    private Boolean notAccountHolder;

    /**
     * fixed income before taxes is the credit card applicant's income before taxes.
     * e.g. 1000.00
     */
    @Column(nullable = false)
    private String fixedIncomeAfterTaxes;

    /**
     * Job stability is the credit card applicant's job stability.
     * e.g.  STABLE, TEMPORAL, NO
     */
    @Column(nullable = false)
    private JobStabilityStatus jobStability;

    @Column(nullable = false)
    private Boolean documentSent;

    @Column(nullable = false)
    private Boolean documentUnsent;

}

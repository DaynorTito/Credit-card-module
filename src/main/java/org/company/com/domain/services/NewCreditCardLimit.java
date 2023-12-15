package org.company.com.domain.services;

import org.company.com.domain.DB.ClientDB;
import org.company.com.domain.model.CreditCardLimit;
import org.company.com.domain.model.JobStabilityStatus;
import org.company.com.domain.model.RequestCreditCardAccount;
import org.company.com.domain.repository.UserRepository;

import java.sql.Connection;
import java.util.UUID;

public class NewCreditCardLimit {

    private EmailNotificationService newLimitEmail;
    private CreditCardLimit newCreditCardLimit;

    public double calculateLimit(String income, JobStabilityStatus stability, UUID id) {
        Connection connection = ClientDB.getInstance().getConnection();
        CreditCardLimit creditCardLimit = new CreditCardLimit();
        double newCreditCardLimit = creditCardLimit.calculateLimit(income, stability);
        UserRepository userRepository = new UserRepository(connection);
        RequestCreditCardAccount foundUser = userRepository.findByID(id);
        newLimitEmail.sendEmailDataCard(foundUser.getEmail(), foundUser.getFirstName(), null, newCreditCardLimit, null);

        return 0;
    }
}

package org.company.com.domain.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.company.com.domain.model.RequestCreditCardAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class RequestCreditCardAccountRepository {

    private final Connection db;

    public UUID insertOne(RequestCreditCardAccount entity) {
        try {

            String query = "INSERT INTO request_credit_card_account (id, first_name, last_name, birth_date, " +
                    "address, dni_number, email, current_corp, current_position, account_holder, not_account_holder, " +
                    "fixed_income_after_taxes, job_stability, document_sent, document_unsent) VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = db.prepareStatement(query);

            preparedStatement.setString(1, entity.getId().toString());
            preparedStatement.setString(2, entity.getFirstName());
            preparedStatement.setString(3, entity.getLastName());
            preparedStatement.setDate(4, new java.sql.Date(entity.getBirthDate().getTime()));
            preparedStatement.setString(5, entity.getAddress());
            preparedStatement.setString(6, entity.getDniNumber());
            preparedStatement.setString(7, entity.getEmail());
            preparedStatement.setString(8, entity.getCurrentCorp());
            preparedStatement.setString(9, entity.getCurrentPosition());
            preparedStatement.setBoolean(10, entity.getAccountHolder());
            preparedStatement.setBoolean(11, entity.getNotAccountHolder());
            preparedStatement.setString(12, entity.getFixedIncomeAfterTaxes());
            preparedStatement.setString(13, entity.getJobStability().toString());
            preparedStatement.setBoolean(14, entity.getDocumentSent());
            preparedStatement.setBoolean(15, entity.getDocumentUnsent());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            System.out.println("Datos registrados exitosamente en la DB");
            return entity.getId();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error al conectar con la base de datos");
            return null;
        }
    }

}

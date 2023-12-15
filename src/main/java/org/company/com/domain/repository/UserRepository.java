package org.company.com.domain.repository;

import lombok.AllArgsConstructor;
import org.company.com.domain.model.RequestCreditCardAccount;
import org.company.com.mock.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@AllArgsConstructor
public class UserRepository {
    private final Connection db;

    public RequestCreditCardAccount findByID(UUID id) {
        RequestCreditCardAccount creditCardApplication = new RequestCreditCardAccount();
        return creditCardApplication;
    }

    public UUID insertOne(User entity) {
        try {
            String query = "INSERT INTO user (id, name, last_name, email, id_request_card) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = db.prepareStatement(query)) {
                preparedStatement.setString(1, entity.getId().toString());
                preparedStatement.setString(2, entity.getName());
                preparedStatement.setString(3, entity.getLastName());
                preparedStatement.setString(4, entity.getEmail());
                preparedStatement.setString(5, entity.getIdRequestCreditCard().toString());

                preparedStatement.executeUpdate();
            }
            return entity.getId();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error inserting data into user table");
            return null;
        }
    }

    public String findIdByEmail(String email) {
        try {
            String query = "SELECT id FROM user WHERE email = ?";
            try (PreparedStatement preparedStatement = db.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.executeQuery();
                preparedStatement.getResultSet().next();
                return preparedStatement.getResultSet().getString("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error inserting data into user table");
            return null;
        }
    }
}

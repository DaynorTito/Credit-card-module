package org.company.com.domain.repository;

import lombok.AllArgsConstructor;
import org.company.com.domain.services.creditCardServices.CreditCard;
import org.company.com.domain.services.creditCardServices.CreditAccount;
import org.company.com.mock.User;

import java.sql.*;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class CreditCardRepository {
    private final Connection db;


    public void insertOne(CreditCard entity) {
        try {
            String insertCreditCardQuery = "INSERT INTO credit_card (id, user_id, card_number, cvv, expiration_date, account_number, available_balance, " +
                    "current_debt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = db.prepareStatement(insertCreditCardQuery, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, entity.getId().toString());
                preparedStatement.setString(2, entity.getUserId().toString());
                preparedStatement.setString(3, entity.getCardNumber());
                preparedStatement.setInt(4, entity.getCvv());
                preparedStatement.setDate(5, java.sql.Date.valueOf(entity.getExpirationDate().toLocalDate()));
                CreditAccount creditAccount = entity.getCreditAccount();
                preparedStatement.setString(6, creditAccount.getAccountNumber());
                preparedStatement.setDouble(7, creditAccount.getAvailableBalance());
                preparedStatement.setDouble(8, creditAccount.getCurrentDebt());
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating credit card failed, no rows affected.");
                }
                System.out.println("Se registraron los datos de targeta en la Base de Datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<CreditCard> findByCardNumber(String cardNumber) {
        try {
            Statement statement = db.createStatement();
            String query = "SELECT * FROM credit_card WHERE card_number = ?";
            CallableStatement callableStatement = db.prepareCall(query);

            callableStatement.setString(1, cardNumber);


            ResultSet resultSet = callableStatement.executeQuery();

            CreditCard creditCard = null;

            if (resultSet.next()) {

                User user = new User(
                        UUID.fromString(resultSet.getString("id")),
                        UUID.fromString(resultSet.getString("id_request_card")),
                        resultSet.getString("email"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name")
                );

                CreditAccount creditAccount = new CreditAccount(
                        resultSet.getString("account_number"),
                        resultSet.getDouble("available_balance"),
                        resultSet.getDouble("credit_limit")
                );

                creditCard = new CreditCard(
                        UUID.fromString(resultSet.getString("user_id")),
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("card_number"),
                        resultSet.getInt("cvv"),
                        resultSet.getTimestamp("expiration_date").toLocalDateTime(),
                        user,
                        creditAccount
                );
            }
            return Optional.ofNullable(creditCard);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error connecting to database");
            return Optional.empty();
        }
    }


    public Optional<CreditCard> findByCardId(String cardNumber) {
        try {
            Statement statement = db.createStatement();
            String query = "SELECT * FROM credit_card WHERE id = ?";
            CallableStatement callableStatement = db.prepareCall(query);

            callableStatement.setString(1, cardNumber);


            ResultSet resultSet = callableStatement.executeQuery();

            CreditCard creditCard = null;

            if (resultSet.next()) {

                User user = new User(
                        UUID.fromString("f436922f-9444-4045-9e80-d0b6200d3f9f"),
                        UUID.fromString("f436922f-9444-4045-9e80-d0b6200d3f9f"),
                        "email",
                        "name",
                        "last_name"
                );

                CreditAccount creditAccount = new CreditAccount(
                        resultSet.getString("account_number"),
                        resultSet.getDouble("available_balance"),
                        0.0
                );

                creditCard = new CreditCard(
                        UUID.fromString(resultSet.getString("user_id")),
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("card_number"),
                        resultSet.getInt("cvv"),
                        resultSet.getTimestamp("expiration_date").toLocalDateTime(),
                        user,
                        creditAccount
                );
            }

            return Optional.ofNullable(creditCard);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error connecting to database");
            return Optional.empty();
        }
    }


    public String findIdByUserId(String userId) {
        try {
            String query = "SELECT id FROM credit_card WHERE user_id = ?";
            try (PreparedStatement preparedStatement = db.prepareStatement(query)) {
                preparedStatement.setString(1, userId);
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

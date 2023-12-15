package org.company.com.domain.repository;

import org.company.com.domain.DB.ClientDB;
import org.company.com.domain.model.Transaction;
import org.company.com.domain.model.TransactionStatus;
import org.company.com.domain.model.TypePayment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionDB {
    public static List<Transaction> getTransactionsByUserId(String userId) {
        List<Transaction> transactions = new ArrayList<>();

        try (Connection connection = ClientDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Transaction t " +
                             "INNER JOIN credit_card c ON t.creditCard = c.id " +
                             "WHERE c.user_id = ? " +
                             "ORDER BY t.createdAt DESC " +
                             "LIMIT 10"


             )) {


            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(UUID.fromString(resultSet.getString("id")));
                transaction.setAmount(resultSet.getBigDecimal("amount").doubleValue());
                transaction.setCreditCard(UUID.fromString(resultSet.getString("creditCard")));
                transaction.setStatus(TransactionStatus.valueOf(resultSet.getString("status")));
                transaction.setConcept(resultSet.getString("concept"));
                transaction.setType(TypePayment.valueOf(resultSet.getString("type")));
                transaction.setCreatedAt(resultSet.getTimestamp("createdAt").toLocalDateTime());

                transactions.add(transaction);
            }
            preparedStatement.close();
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("Error al obtener las transacciones del usuario");
            e.printStackTrace();
        }

        return transactions;
    }

}
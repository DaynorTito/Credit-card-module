package org.company.com.domain.repository;

import org.company.com.domain.DB.ClientDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CreditCardUserData {
    // Método para obtener el ResultSet con la información del usuario por su ID
    public ResultSet getUserAccountInfoById(UUID userId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = ClientDB.getInstance().getConnection();
            String sqlQuery = "SELECT c.account_number, u.name, u.last_name\n" + "FROM credit_card c\n" + "INNER JOIN user u ON u.id = c.user_id\n" + "WHERE u.id = ?";
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, userId.toString());
            resultSet = preparedStatement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            System.out.println("Error al obtener la información del usuario");
            System.out.println(e.getMessage());
            return null;
        } finally {
//            if (preparedStatement != null) preparedStatement.close();
//            if (connection != null) connection.close();
        }
    }
}
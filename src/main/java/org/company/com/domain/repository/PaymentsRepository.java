package org.company.com.domain.repository;

import org.company.com.domain.model.payment.AutomaticPayment;
import org.company.com.domain.model.payment.PaymentStatus;
import org.company.com.domain.DB.ClientDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;


public class PaymentsRepository {

    private Connection db;

    private ResultSet execute(String query, String arg) throws SQLException {
        this.db = ClientDB.getInstance().getConnection();
        Statement statement = db.createStatement();
        CallableStatement callableStatement = db.prepareCall(query);
        callableStatement.setString(1, arg);

        return callableStatement.executeQuery();
    }

    private void update(String query) throws SQLException {
        db = ClientDB.getInstance().getConnection();
        Statement statement = db.createStatement();
        statement.executeUpdate(query);
    }

    public Optional<AutomaticPayment> findPaymentByCardId(String cardNumber) {

        try {
            ResultSet resultSet = execute("SELECT * FROM automatic_payment WHERE credit_card = ?",
                    cardNumber);
            AutomaticPayment payment = new AutomaticPayment();
            resultSet.next();
            payment.setId(UUID.fromString(resultSet.getString("id")));
            payment.setAmount(resultSet.getDouble("amount"));
            //payment.setStatus();
            payment.setRepeatMonthlyAt(Date.valueOf(resultSet.getString("repeat_monthly")).toLocalDate());
            payment.setCreatedAt(Date.valueOf(resultSet.getString("repeat_monthly")).toLocalDate());
            payment.setCreditCard(UUID.fromString(resultSet.getString("credit_card")));
            payment.setAccount(UUID.fromString(resultSet.getString("account_number")));

            return Optional.of(payment);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void addPaymentByCardId(String amount, LocalDate fecha, String cardId, String account) {
        try {
            update("INSERT INTO "
                    + "automatic_payment(id, amount, payment_status, repeat_monthly, created_at, credit_card, account_number) "
                    + "VALUES " +
                    "('" + UUID.randomUUID() + "', " +
                    amount + ", '" +
                    PaymentStatus.APPROVED.toString() + "', '" +
                    fecha.toString() + "', '" +
                    LocalDate.now() + "', '" +
                    cardId.toString() + "', '" +
                    account + "');");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePaymentByCardId(String amount, LocalDate fecha, String cardId, String account) {
        try {
            update("UPDATE automatic_payment SET " +
                    "amount = " + amount +
                    ", payment_status = '" + PaymentStatus.APPROVED.toString() +
                    "', repeat_monthly = '" + fecha.toString() +
                    "', created_at = '" + LocalDate.now() +
                    "', credit_card = '" + cardId +
                    "', account_number = '" + account +
                    "' WHERE credit_card = '" + cardId + "'");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

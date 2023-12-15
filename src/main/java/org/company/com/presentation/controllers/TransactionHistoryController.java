package org.company.com.presentation.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.company.com.domain.model.Transaction;
import org.company.com.domain.model.TypePayment;
import org.company.com.domain.repository.CreditCardUserData;
import org.company.com.domain.repository.TransactionDB;
import org.company.com.presentation.utils.WindowsRedirectController;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TransactionHistoryController {

    public String userId;
    public String creditCardId;

    @FXML
    private Label AccountLabel;
    @FXML
    private TableView<Transaction> TableHistory;
    @FXML
    private Button accountBalanceButton;
    @FXML
    private Label accountLabel;
    @FXML
    private TableColumn<?, ?> amountTableColumn;
    @FXML
    private TableColumn<?, ?> balanceTableColumn;
    @FXML
    private Label cardholderLabel;
    @FXML
    private Button dataRectificationButton;
    @FXML
    private TableColumn<?, ?> dateTableColmn;
    @FXML
    private TableColumn<?, ?> descriptionTableColumn;
    @FXML
    private TableColumn<?, ?> invoiceNumberTableColumn;
    @FXML
    private Label rangeOfHistoryLabel;
    @FXML
    private TextField rangeOfHistoryTextField;
    @FXML
    private Button scheduleAutomaticPaymentsButton;
    @FXML
    private Button transactionHistoryButton;
    @FXML
    private Button logOutButton;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCreditCardId(String creditCardId) {
        this.creditCardId = creditCardId;
    }


    public void initializeTable() {
        System.out.println(">> initializeTable()");
       
        // Get the latest 10 transactions for a specific user
        List<Transaction> transactionsList = TransactionDB.getTransactionsByUserId(this.userId);

        // print the list of transactions
        for (Transaction transaction : transactionsList) {
            System.out.println(transaction.getAmount());
        }

        // If there are more than 10 transactions, limit to the latest 10
        ObservableList<Transaction> observableTransactions;

        if (transactionsList.size() > 10) {
            observableTransactions = FXCollections.observableList(transactionsList.subList(0, 10));
        } else {
            observableTransactions = FXCollections.observableList(transactionsList);
        }

        // Clear existing columns in the TableView
//        TableHistory.getColumns().clear();

        // Create and configure the 'ID' column
        TableColumn<Transaction, String> columnId = new TableColumn<>("ID");
        columnId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId().toString()));
        columnId.setPrefWidth(228);

        // Create and configure the 'Date' column
        TableColumn<Transaction, LocalDateTime> columnDate = new TableColumn<>("Fecha");
        columnDate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreatedAt()));
        columnDate.setPrefWidth(191);

        // Create and configure the 'Description' column
        TableColumn<Transaction, String> columnDescription = new TableColumn<>("Descripcion");
        columnDescription.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getConcept()));
        columnDescription.setPrefWidth(365);

        // Create and configure the 'Amount' column
        TableColumn<Transaction, Double> columnAmount = new TableColumn<>("Cantidad");
        columnAmount.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAmount()));
        columnAmount.setPrefWidth(88);

//        // Create and configure the 'Type' column
        TableColumn<Transaction, String> columnType = new TableColumn<>("Tipo");
        columnType.setCellValueFactory(cellData -> {
            if (cellData.getValue().getType() == TypePayment.INCOME) return new SimpleStringProperty("Ingreso");
            else return new SimpleStringProperty("Egreso");
        });
        columnType.setPrefWidth(88);

        // block resizing the columns
        TableHistory.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);


        // Add columns to the TableView
        TableHistory.getColumns().addAll(columnId, columnDate, columnDescription, columnAmount, columnType);

        // Show the data in the TableView
        TableHistory.setItems(observableTransactions);

    }


    public void initializeFields() {
        System.out.println(">> initializeFields()");
        // Get data from CreditCardUserData and set it in the fields
        try {
            UUID userId = UUID.fromString(this.userId);
            CreditCardUserData creditCardUserData = new CreditCardUserData();
            ResultSet resultSet = creditCardUserData.getUserAccountInfoById(userId);

            if (resultSet.next()) {
                // Get data from the ResultSet
                String accountNumber = resultSet.getString("account_number");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");

                // Set values in the corresponding fields
                cardholderLabel.setText(name + " " + lastName);
                AccountLabel.setText(accountNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void goToAccountBalanceTHEvent(ActionEvent event) throws IOException {
        WindowsRedirectController.goToAccountBalance(event, this.userId, this.creditCardId);
    }

    @FXML
    void goToMovementHistoryTHEvent(ActionEvent event) throws IOException {
        WindowsRedirectController.goToTransactionHistory(event, this.userId, this.creditCardId);
    }

    @FXML
    void goToScheduleAutomaticPaymentsTHEvent(ActionEvent event) throws IOException {
        WindowsRedirectController.goToProgramPayments(event, this.userId, this.creditCardId);
    }

    @FXML
    void logOutEvent(ActionEvent event) throws IOException {
        WindowsRedirectController.goToHomePage(event);
    }
}
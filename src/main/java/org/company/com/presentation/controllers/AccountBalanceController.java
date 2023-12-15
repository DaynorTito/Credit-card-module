package org.company.com.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.company.com.domain.repository.PaymentsRepository;
import org.company.com.domain.services.MinimumPaymentCalculator;
import org.company.com.domain.DB.ClientDB;
import org.company.com.domain.repository.CreditCardRepository;
import org.company.com.domain.services.creditCardServices.CreditCard;
import org.company.com.presentation.utils.WindowsRedirectController;

import java.sql.Connection;
import java.util.Optional;

public class AccountBalanceController {


    private String accountId;
    private String creditCardId;
    private MinimumPaymentCalculator minimumPaymentCalculator;

    @FXML
    private TextField accountTextField;

    @FXML
    private Button accountBalanceButton;

    @FXML
    private Label accountLabel;

    @FXML
    private Label cardDetailsLabel;

    @FXML
    private Label cardNumberLabel;

    @FXML
    private TextField cardNumberTextField;

    @FXML
    private Label cardholderLabel;

    @FXML
    private TextField cardholderTextField;

    @FXML
    private Label countryLabel;

    @FXML
    private TextField countryTextField;

    @FXML
    private Label currentBalanceLabel;

    @FXML
    private TextField currentBalanceTextField;

    @FXML
    private Label currentDebtLabel;

    @FXML
    private TextField currentDebtTextField;

    @FXML
    private Label expirationDateLabel;

    @FXML
    private TextField expirationDateTextField;

    @FXML
    private Button logOutButton;

    @FXML
    private Button makeAutomaticPaymentButton;

    @FXML
    private Label minimumPaymentLabel;

    @FXML
    private TextField minimumPaymentTexField;

    @FXML
    private Label nextPaymentDateLabel;

    @FXML
    private TextField nextPaymentDateTextField;

    @FXML
    private Button scheduleAutomaticPaymentsButton;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField statusTextField;

    @FXML
    private Button transactionHistoryButton;

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setCreditCardId(String creditCardId) {
        this.creditCardId = creditCardId;
    }

    @FXML
    void goToAccountBalanceABEvent(ActionEvent event) {
        WindowsRedirectController.goToAccountBalance(event, accountId, creditCardId);
    }

    @FXML
    void goToMovementHistoryABEvent(ActionEvent event) {
        WindowsRedirectController.goToTransactionHistory(event, accountId, creditCardId);
    }

    @FXML
    void goToScheduleAutomaticPaymentsABEvent(ActionEvent event) {
        WindowsRedirectController.goToProgramPayments(event, accountId, creditCardId);
    }

    @FXML
    void logOutEvent(ActionEvent event) {
        WindowsRedirectController.goToHomePage(event);

    }

    @FXML
    void makeAutomaticPaymentEvent(ActionEvent event) {

    }

    public void initialize(String accountId, String creditCardId){
        System.out.println(">> Initialize Balance");
        Connection db = ClientDB.getInstance().getConnection();
        CreditCardRepository creditCardRepository = new CreditCardRepository(db);
        PaymentsRepository paymentsRepository = new PaymentsRepository();
        Optional<CreditCard> creditCardOptional = creditCardRepository.findByCardId(creditCardId);

     if (creditCardOptional.isPresent()) {
        CreditCard creditCard = creditCardOptional.get();

        cardholderTextField.setText("David Lezama");
        accountTextField.setText(accountId);
        cardNumberTextField.setText(creditCard.getCardNumber());
        expirationDateTextField.setText(String.valueOf(creditCard.getExpirationDate().toLocalDate()));
        currentBalanceTextField.setText(String.valueOf(creditCard.getCreditAccount().getAvailableBalance()));
        currentDebtTextField.setText(String.valueOf(creditCard.getCreditAccount().getCurrentDebt()));
        minimumPaymentTexField.setText("200");

//        AutomaticPayment automaticPayment = automaticPaymentOptional.get();
//        LocalDateTime nextPaymentDate = automaticPayment.getCreatedAt().plusMonths(1).atStartOfDay();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        String formattedNextPaymentDate = nextPaymentDate.format(formatter);
        nextPaymentDateTextField.setText("29-12-2024");
    }
    }

}

package org.company.com.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.company.com.controllers.AutomaticPaymentController;
import org.company.com.controllers.exceptions.ControllerException;
import org.company.com.presentation.utils.WindowsRedirectController;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProgramPaymentsController implements Initializable {


    private final String accountId = "23177e10-eee3-4aa7-ad96-a4c2ec310f73";
    private final String creditCardId = "61ad3593-a397-4c01-8a98-2742f7411629";

    private String accountIdToPass = null;
    private String creditCardIdToPass = null;


    @FXML
    private Button accountBalanceButton;
    @FXML
    private Label amountLabel;
    @FXML
    private TextField amountTextField;
    @FXML
    private Label automaticPaymentLabel;
    @FXML
    private Button backToTopButton;
    @FXML
    private Label cardholderLabel;
    @FXML
    private Button confirmPaymentButton;
    @FXML
    private Label linkedAccountLabel;
    @FXML
    private Label linkedAccountLabelText;
    @FXML
    private Label nextPaymentDateLabel;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button scheduleAutomaticPaymentsButton;
    @FXML
    private Button singOutButton;
    @FXML
    private Button transactionHistoryButton;
    private AutomaticPaymentController controller = new AutomaticPaymentController();

    public void setAccountIdToPass(String accountIdToPass) {
        this.accountIdToPass = accountIdToPass;
    }

    public void setCreditCardIdToPass(String creditCardIdToPass) {
        this.creditCardIdToPass = creditCardIdToPass;
    }

    @FXML
    void confirmPaymentEvent(ActionEvent actionEvent) {
        try {
            controller.addPayment(amountTextField.getText(), datePicker.getValue(), creditCardId);
            renderAlertInformation("Pago añadido correctamente");
        } catch (ControllerException e) {
            String msg = e.getMessage();
            renderAlertAlert(msg);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] result = controller.getAccountData(accountId);

        cardholderLabel.setText(result[0]);

        linkedAccountLabelText.setText(result[1]);

        result = controller.getPaymentData(creditCardId);

        amountTextField.setText(result[0]);
        datePicker.setValue(LocalDate.parse(result[1]));
    }

    @FXML
    void getDate() {
        LocalDate date = datePicker.getValue();
    }

    @FXML
    void backToTopEvent(ActionEvent actionEvent) {
        WindowsRedirectController.goToHomePage(actionEvent);
    }

    @FXML
    void logOutEvent(ActionEvent actionEvent) {
        WindowsRedirectController.goToHomePage(actionEvent);
    }

    @FXML
    void goToMovementHistoryPPEvent(ActionEvent actionEvent) {
        WindowsRedirectController.goToTransactionHistory(actionEvent, accountIdToPass, creditCardIdToPass);
    }

    @FXML
    void goToAccountBalancePPEvent(ActionEvent actionEvent) {
        WindowsRedirectController.goToAccountBalance(actionEvent, accountIdToPass, creditCardIdToPass);
    }

    @FXML
    void goToScheduleAutomaticPaymentsPPEvent(ActionEvent actionEvent) {
        WindowsRedirectController.goToProgramPayments(actionEvent, accountIdToPass, creditCardIdToPass);
    }


    public void renderAlertAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ocurrió un error");
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setContentText(msg);

        alert.showAndWait();
    }

    public void renderAlertInformation(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setContentText(msg);

        alert.showAndWait();
    }

}

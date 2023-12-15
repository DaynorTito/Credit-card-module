package org.company.com.presentation.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.company.com.presentation.controllers.AccountBalanceController;
import org.company.com.presentation.controllers.ProgramPaymentsController;
import org.company.com.presentation.controllers.TransactionHistoryController;

import java.io.IOException;

public class WindowsRedirectController {

    private static final String FXML_PATH_HOME_PAGE = "/views/home-page.fxml";
    private static final String FXML_PATH_ACCOUNT_BALANCE = "/views/AccountBalance.fxml";
    private static final String FXML_PATH_PROGRAM_PAYMENTS = "/views/ProgramPayments.fxml";
    private static final String FXML_PATH_TRANSACTION_HISTORY = "/views/TransactionHistory.fxml";


    private static void redirectTo(String path, ActionEvent event) {

        try {
            Parent root = new FXMLLoader(WindowsRedirectController.class.getResource(path)).load();
            Scene scene = new Scene(root);

            Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

            currentWindow.setScene(scene);
            currentWindow.show();

        } catch (IOException e) {
            redirectError(e.getMessage());
        }

    }

    public static void goToHomePage(ActionEvent event) {
        redirectTo(FXML_PATH_HOME_PAGE, event);
    }

    public static void goToAccountBalance(ActionEvent event, String userId, String creditCardId) {
        System.out.println(" >>>> userId: " + userId);
        System.out.println(" >>>> creditCardId: " + creditCardId);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WindowsRedirectController.class.getResource(FXML_PATH_ACCOUNT_BALANCE));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            AccountBalanceController controller = fxmlLoader.getController();
            controller.setAccountId(userId);
            controller.setCreditCardId(creditCardId);
            controller.initialize(userId, creditCardId);

            Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

            currentWindow.setScene(scene);
            currentWindow.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void goToProgramPayments(ActionEvent event, String userId, String creditCardId) {
        System.out.println(" >>>> userId: " + userId);
        System.out.println(" >>>> creditCardId: " + creditCardId);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WindowsRedirectController.class.getResource(FXML_PATH_PROGRAM_PAYMENTS));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            ProgramPaymentsController controller = fxmlLoader.getController();
            controller.setAccountIdToPass(userId);
            controller.setCreditCardIdToPass(creditCardId);

            Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

            currentWindow.setScene(scene);
            currentWindow.show();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void goToTransactionHistory(ActionEvent event, String userId, String creditCardId) {
        System.out.println(" >>>> userId: " + userId);
        System.out.println(" >>>> creditCardId: " + creditCardId);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WindowsRedirectController.class.getResource(FXML_PATH_TRANSACTION_HISTORY));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            TransactionHistoryController controller = fxmlLoader.getController();

            controller.setUserId(userId);
            controller.setCreditCardId(creditCardId);
            controller.initializeFields();
            controller.initializeTable();


            Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

            currentWindow.setScene(scene);
            currentWindow.show();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void redirectError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(msg);
        alert.setGraphic(null);
        alert.showAndWait();
    }
}

package org.company.com.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Setter;
import org.company.com.domain.DB.ClientDB;
import org.company.com.domain.repository.CreditCardRepository;
import org.company.com.domain.repository.UserRepository;

import java.sql.Connection;


public class DialogViewController {

    private static final String FXML_PATH_APP = "/views/TransactionHistory.fxml";

    @FXML
    public VBox dialogVBox;

    @FXML
    public TextField inputCode;

    @FXML
    private Label emailTo;

    @Setter
    private String code;

    @Setter
    private Stage window;

    @Setter
    private Stage stageDialog;

    public void validCode() {
        String inputCode = this.inputCode.getText();

        if (!inputCode.equals(code)) {
            alertError();
            return;
        }

        try {
            stageDialog.close();

            Connection connection = ClientDB.getInstance().getConnection();
            UserRepository userRepository = new UserRepository(connection);
            CreditCardRepository creditCardRepository = new CreditCardRepository(connection);

            String userId = userRepository.findIdByEmail(emailTo.getText());
            String creditCardId = creditCardRepository.findIdByUserId(userId);

            System.out.println(" >>>> userId: " + userId);
            System.out.println(" >>>> creditCardId: " + creditCardId);

            FXMLLoader root = new FXMLLoader(getClass().getResource(FXML_PATH_APP));
            Scene scene = new Scene(root.load());


            TransactionHistoryController controller = root.getController();

            controller.setUserId(userId);
            controller.setCreditCardId(creditCardId);
            controller.initializeFields();
            controller.initializeTable();

            window.setScene(scene);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public void displayEmail(String email) {
        emailTo.setText(email);
    }


    public void alertError() {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");
        alert.setGraphic(null);
        alert.setHeaderText("Codigo incorrecto");
        alert.setContentText("El codigo ingresado es incorrecto o ya expiro");

        alert.showAndWait();


    }

}

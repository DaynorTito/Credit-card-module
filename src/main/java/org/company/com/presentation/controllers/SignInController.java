package org.company.com.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.company.com.domain.model.Controller;
import org.company.com.domain.services.EmailNotificationService;
import org.company.com.controllers.SendCodeController;


public class SignInController {

    private static final String FXML_PATH_SEND_CODE = "/views/dialog-code.fxml";
    private static final String FXML_PATH_HOME = "/views/home-page.fxml";

    @FXML
    public AnchorPane container;

    @FXML
    private TextField email;


    @FXML
    public void sendCodeToEmail() {
        final String email = this.email.getText();

        if (!isEmailValid(email)) {
            showErrorMessage();
            return;
        }

        try {

            final Stage stage = new Stage();
            final FXMLLoader root = new FXMLLoader(getClass().getResource(FXML_PATH_SEND_CODE));
            final Scene scene = new Scene(root.load());


            EmailNotificationService emailNotificationService = new EmailNotificationService();
            Controller<String, String> controller = new SendCodeController(emailNotificationService);

            String code = controller.execute(email);

            System.out.println("Code: " + code);

            DialogViewController dialogViewController = root.getController();
            dialogViewController.displayEmail(email);
            dialogViewController.setCode(code);
            dialogViewController.setStageDialog(stage);

            Stage window = (Stage) this.container.getScene().getWindow();
            dialogViewController.setWindow(window);

            stage.setTitle("Codigo de verificacion");
            stage.setScene(scene);
            stage.showAndWait();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isEmailValid(final String email) {
        return email.contains("@");
    }

    public void showErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Correo Electronico Invalido");
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setContentText("El correo electronico ingresado no es valido");

        alert.showAndWait();
    }


    public void navigateToRequest() {
        try {
            Pane root = new FXMLLoader(getClass().getResource(FXML_PATH_HOME)).load();

            Scene scene = new Scene(root);
            Stage window = (Stage) this.container.getScene().getWindow();

            window.setScene(scene);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

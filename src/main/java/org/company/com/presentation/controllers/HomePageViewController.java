package org.company.com.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HomePageViewController {
    private static final String FXML_PATH_SIGN_IN = "/views/sign-in.fxml";
    private static final String FXML_PATH_REQUEST = "/views/CardApplicationFinancialInformation.fxml";


    @FXML
    public Button signIn;

    @FXML
    public Button requestCard;

    @FXML
    public AnchorPane container;

    public void renderSignInWithEmail() {
        renderPage(FXML_PATH_SIGN_IN);
    }

    public void renderRequestCreditCard() {
        System.out.println("renderRequestCreditCard");
        renderPage(FXML_PATH_REQUEST);
    }


    private void renderPage(String fxmlPath) {
        try {
            Pane root = new FXMLLoader(getClass().getResource(fxmlPath)).load();
            Scene scene = new Scene(root);

            Stage window = (Stage) container.getScene().getWindow();

            window.setScene(scene);
            System.out.println("renderPage");
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
    }

}

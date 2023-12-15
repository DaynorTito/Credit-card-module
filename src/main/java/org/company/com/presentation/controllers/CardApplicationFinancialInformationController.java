package org.company.com.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.company.com.domain.model.JobStabilityStatus;
import org.company.com.domain.Validations;
import org.company.com.domain.model.RequestCreditCardAccount;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class CardApplicationFinancialInformationController {

  private static final String FXML_PATH_LABOR_INFORMATION = "/views/CardApplicationEmploymentInformation.fxml";

  private static final String FORMAT_DATE = "MM/dd/yyyy";
  private Validations validations = new Validations();

  private RequestCreditCardAccount requestCreditCard;

  @FXML
  private Label CountryLabel;

  @FXML
  private Label PersonalInformationLabel;

  @FXML
  private Button cancelButton;

  @FXML
  private Label cardApplicationLabel;

  @FXML
  private Button continueButton;

  @FXML
  private Label dateOfBirthLabel;


  @FXML
  private Label departmentLabel;

  @FXML
  private Label emailAddressLabel;

  @FXML
  private TextField emailAddressTextField;

  @FXML
  private Label homeAddressLabel;

  @FXML
  private TextField homeAddressTextFIeld;

  @FXML
  private Label identityCardLabel;

  @FXML
  private TextField identityCardTextField;

  @FXML
  private Label lastNameLabel;

  @FXML
  private TextField lastNameTextField;

  @FXML
  private Label nameLabel;

  @FXML
  private TextField nameTextField;

  @FXML
  private Button step1Button;

  @FXML
  private Label telephoneNumberLabel;

  @FXML
  private TextField telephoneNumberTextField;

  @FXML
  private DatePicker dateOfBirthDate;

  @FXML
  private TextField countryTextField;

  @FXML
  private TextField departamentTextField;



  @FXML
  void cancelEvent(ActionEvent event) {

  }


  @FXML
  void continueEvent(ActionEvent event) {

    LocalDate selectedDate = dateOfBirthDate.getValue();
    String name = nameTextField.getText();
    String lastName = lastNameTextField.getText();
    String identityDNI = identityCardTextField.getText();
    String telefoneNumber = telephoneNumberTextField.getText();
    String email = emailAddressTextField.getText();
    String address = homeAddressTextFIeld.getText();

    if (validateFields(selectedDate,name,lastName,identityDNI,telefoneNumber,email)) {
      try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        String dateofbirth = selectedDate.format(formatter);
        Date dateOfBirth = convertToDate(dateofbirth);

        requestCreditCard = new RequestCreditCardAccount(UUID.randomUUID(), name, lastName, dateOfBirth, address, identityDNI, email,
                "corp", "position", true, true, "0.0",
                JobStabilityStatus.NO, true, true);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH_LABOR_INFORMATION));
        Pane root = loader.load();
        CardApplicationEmploymentInformationController employmentController = loader.getController();
        employmentController.setRequestCreditCard(requestCreditCard);
        Scene newScene = new Scene(root);
        Scene currentScene = cancelButton.getScene();
        Stage window = (Stage) currentScene.getWindow();
        window.setScene(newScene);
        window.show();

      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }


  public boolean validateFields(LocalDate selectedDate, String name, String lastName, String identityDNI, String telefoneNumber, String email){
    if (validations.onlyLetters(name) && validations.onlyLetters(lastName) && selectedDate != null
             && validations.dateOfBirth(selectedDate.toString()) && validations.validateEmails(email)
             && validations.validateDNI(identityDNI) && validations.isNumner(telefoneNumber)) {
        return true;
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Datos invalidos");
      alert.setHeaderText(null);
      alert.setGraphic(null);
      alert.setContentText("Revise que los datos ingresados en el formulario sean correctos e intente nuevamente" +
              " Debe tener una edad mayor igual a 18, el DNI debe ser de mas de 7 digitos y letras");
      Optional<ButtonType> result = alert.showAndWait();

      if (result.isPresent() && result.get() == ButtonType.OK) {
        System.out.println("OK");
      } else {
        System.out.println("Error");
      }
      return false;
    }
  }

  public static Date convertToDate(String dateString) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
    try {
      return dateFormat.parse(dateString);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

}

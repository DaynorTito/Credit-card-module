package org.company.com.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.company.com.controllers.RequestCreditCardController;
import org.company.com.domain.Response.ResponseCreditCard;
import org.company.com.domain.model.*;
import org.company.com.domain.repository.RequestCreditCardRepository;
import org.company.com.domain.services.EmailNotificationService;
import org.company.com.domain.services.RequestCreditCardServiceImpl;
import org.company.com.domain.services.creditCardServices.CardNumberGenerator;
import org.company.com.domain.services.creditCardServices.CreditCardService;
import org.company.com.domain.services.creditCardServices.ExpirationDateGenerator;
import org.company.com.mock.MockTableRequestCreditCard;
import org.jala.com.domain.model.*;
import org.company.com.controllers.CreateCreditCardController;
import org.company.com.controllers.Requests.DataCreateCreditCardRequest;
import org.company.com.domain.DB.ClientDB;
import org.company.com.domain.Response.ResponseRequestCreditCardStatus;
import org.company.com.domain.Validations;
import org.jala.university.domain.model.*;
import org.company.com.domain.repository.CreditCardRepository;
import org.company.com.domain.repository.RequestCreditCardAccountRepository;
import org.company.com.domain.repository.UserRepository;
import org.company.com.domain.services.creditCardServices.CvvGenerator;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class CardApplicationEmploymentInformationController {

    private static final String FXML_PATH_PERSONAL_INFORMATION = "/views/CardApplicationFinancialInformation.fxml";
    private static final String FXML_PATH_SIGN_IN = "/views/sign-in.fxml";

    private EmailNotificationService emailNotificationService = new EmailNotificationService();
    private RequestCreditCardAccount requestCreditCard;
    private DataCreateCreditCardRequest dataCreateCreditCard;
    private Connection connection = ClientDB.getInstance().getConnection();

    @FXML
    private AnchorPane container;

    @FXML
    private Label PersonalInformationLabel;

    @FXML
    private Button backButton;

    @FXML
    private Label cardApplicationLabel;

    @FXML
    private Label dateOfHireLabel;

    @FXML
    private Button finishButton;

    @FXML
    private Label monthlyIncomeLabel;

    @FXML
    private TextField monthlyIncomeTextField;

    @FXML
    private Label nameOfPlaceOfWorkLabel;

    @FXML
    private TextField nameOfPlaceOfWorkTextField;

    @FXML
    private Label referenceNumberLabel;

    @FXML
    private TextField referenceNumberTextField;

    @FXML
    private Button step2Button;

    @FXML
    private ComboBox<String> typeOfEmploymentComboBox;

    @FXML
    private Label typeOfEmploymentLabel;

    @FXML
    private Label workAddressLabel;

    @FXML
    private TextField workAddressTextField;

    @FXML
    private DatePicker dateHire;


    @FXML
    private void initialize() {
        typeOfEmploymentComboBox.getItems().addAll("Estable", "Temporal", "Ninguno");
        typeOfEmploymentComboBox.setValue("Ninguno");

    }

    @FXML
    void backEvent(ActionEvent event) {
        try {

            Pane root = new FXMLLoader(getClass().getResource(FXML_PATH_PERSONAL_INFORMATION)).load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) backButton.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void finishEvent(ActionEvent event) {
        String nameComp = nameOfPlaceOfWorkTextField.getText();
        String workAddress = workAddressTextField.getText();
        LocalDate selectedDate = dateHire.getValue();

        String referenceNumber = referenceNumberTextField.getText();
        String monthlyIcome = monthlyIncomeTextField.getText();
        String typeJob = typeOfEmploymentComboBox.getValue();
        if (validateFields(selectedDate, referenceNumber, monthlyIcome)) {
            try {
                if (!typeJob.equalsIgnoreCase("ninguno")) {
                    getRequestCreditCardData(nameComp, monthlyIcome, typeJob);
                    getCreditCardData(monthlyIcome);

                    Pane root = new FXMLLoader(getClass().getResource(FXML_PATH_SIGN_IN)).load();
                    Scene scene = new Scene(root);

                    Stage window = (Stage) container.getScene().getWindow();

                    window.setScene(scene);

                } else {
                    emailNotificationService.sendEmailApplicationStatus(requestCreditCard.getEmail(), RequestCreditCardStatus.REJECTED);
                    // salir de la solicitud
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void getCreditCardData(String monthlyIcome) {
        dataCreateCreditCard = new DataCreateCreditCardRequest(monthlyIcome, requestCreditCard.getJobStability(), requestCreditCard.getFirstName(), requestCreditCard.getLastName(), requestCreditCard.getEmail(), UUID.randomUUID());
        Controller<ResponseCreditCard, DataCreateCreditCardRequest> controllerCreditCard;
        EmailNotificationService emailNotificationService = new EmailNotificationService();
        CreditCardService creditCardService = new CreditCardService();
        CreditCardLimit creditCardLimit = new CreditCardLimit();
        CardNumberGenerator cardNumberGenerator = new CardNumberGenerator();
        CvvGenerator cvvGenerator = new CvvGenerator();
        ExpirationDateGenerator expirationDateGenerator = new ExpirationDateGenerator();
        CreditCardRepository creditCardRepository = new CreditCardRepository(connection);
        UserRepository userRepository = new UserRepository(connection);
        controllerCreditCard = new CreateCreditCardController(emailNotificationService, creditCardService, creditCardLimit, cardNumberGenerator, cvvGenerator, expirationDateGenerator, creditCardRepository, userRepository, requestCreditCard);
        try {
            controllerCreditCard.execute(dataCreateCreditCard);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getRequestCreditCardData(String nameComp, String monthlyIcome, String typeJob) throws Exception {
        requestCreditCard.setCurrentCorp(nameComp);
        requestCreditCard.setFixedIncomeAfterTaxes(monthlyIcome);
        selectJobType(typeJob);

        Controller<ResponseRequestCreditCardStatus, RequestCreditCardAccount> controllerRequest;
        RequestCreditCardAccountRepository requestCreditCardAccountRepository = new RequestCreditCardAccountRepository(connection);
        MockTableRequestCreditCard mockRequestCreditCardDB = new MockTableRequestCreditCard();
        RequestCreditCardRepository requestCreditCardRepository = new RequestCreditCardRepository(mockRequestCreditCardDB);
        RequestCreditCardServiceImpl requestCreditCardService = new RequestCreditCardServiceImpl(requestCreditCardRepository);

        controllerRequest = new RequestCreditCardController(requestCreditCardAccountRepository, requestCreditCardRepository, requestCreditCardService, emailNotificationService);
        controllerRequest.execute(requestCreditCard);
        emailNotificationService.sendEmailApplicationStatus(requestCreditCard.getEmail(), RequestCreditCardStatus.APPROVED);
    }

    private void selectJobType(String typeJob) {
        JobStabilityStatus jobStatus;
        if (typeJob.equalsIgnoreCase("estable")) jobStatus = JobStabilityStatus.STABLE;
        else if (typeJob.equalsIgnoreCase("temporal")) jobStatus = JobStabilityStatus.TEMPORAL;
        else jobStatus = JobStabilityStatus.NO;
        requestCreditCard.setJobStability(jobStatus);
    }

    private double generateBalance(String income, String typeJob) {
        JobStabilityStatus jobStatus;
        CreditCardLimit creditLimit = new CreditCardLimit();
        if (typeJob.equalsIgnoreCase("estable")) jobStatus = JobStabilityStatus.STABLE;
        else if (typeJob.equalsIgnoreCase("temporal")) jobStatus = JobStabilityStatus.TEMPORAL;
        else jobStatus = JobStabilityStatus.NO;
        requestCreditCard.setJobStability(jobStatus);
        return creditLimit.calculateLimit(income, jobStatus);
    }

    @FXML
    void selectTypeOfEmployment(ActionEvent event) {

    }

    public boolean validateFields(LocalDate dateHire, String referenceNumber, String monthlyIcome) {
        Validations validations = new Validations();
        if (dateHire != null && validations.isNumner(referenceNumber) && validations.validateMonthlyIncome(monthlyIcome)) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datos invalidos");
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setContentText("Revise que los datos ingresados en el formulario sean correctos e intente nuevamente");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("OK");
            } else {
                System.out.println("Error");
            }
            return false;
        }
    }

    public void setRequestCreditCard(RequestCreditCardAccount requestCreditCard) {
        this.requestCreditCard = requestCreditCard;
    }
}

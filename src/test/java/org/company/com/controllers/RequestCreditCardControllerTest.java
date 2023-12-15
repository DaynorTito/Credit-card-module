package org.company.com.controllers;

import org.company.com.domain.DB.ClientDB;
import org.company.com.domain.Response.ResponseRequestCreditCardStatus;
import org.company.com.domain.model.Controller;
import org.company.com.domain.repository.RequestCreditCardAccountRepository;
import org.company.com.domain.repository.RequestCreditCardRepository;
import org.company.com.domain.services.EmailNotificationService;
import org.company.com.domain.services.RequestCreditCardServiceImpl;
import org.company.com.mock.MockTableRequestCreditCard;
import org.company.com.domain.model.RequestCreditCardAccount;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;

class RequestCreditCardControllerTest {

    Controller<ResponseRequestCreditCardStatus, RequestCreditCardAccount> controller;

    @BeforeEach
    void setUp() {
        Connection connection = ClientDB.getInstance().getConnection();
        RequestCreditCardAccountRepository requestCreditCardAccountRepository = new RequestCreditCardAccountRepository(connection);
        MockTableRequestCreditCard mockRequestCreditCardDB = new MockTableRequestCreditCard();
        RequestCreditCardRepository requestCreditCardRepository = new RequestCreditCardRepository(mockRequestCreditCardDB);
        RequestCreditCardServiceImpl requestCreditCardService = new RequestCreditCardServiceImpl(requestCreditCardRepository);
        EmailNotificationService emailNotificationService = new EmailNotificationService();

        controller = new RequestCreditCardController(requestCreditCardAccountRepository, requestCreditCardRepository, requestCreditCardService, emailNotificationService);

    }


    /*@Test
    public void testRequestException() {
        // Arrange
        RequestCreditCardAccount request = null;

        // Act
        RequestNullException exception = assertThrows(RequestNullException.class, () -> controller.execute(null));

        // Assert
        assertEquals(RequestNullException.msg, exception.getMessage());
    }


    @Test
    public void testReturnRequestCreditCardStatus() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        Date birthDate = new Date();
        RequestCreditCardAccount request = new RequestCreditCardAccount(uuid, "David", "Lezama", birthDate, "", "", "test@test.com", "", "", true, false, String.valueOf(2300), JobStabilityStatus.STABLE, true, true);

        // Act
        ResponseRequestCreditCardStatus response = assertDoesNotThrow(() -> controller.execute(request));

        // Assert
        assertTrue(response.isRequestSent());
        assertTrue(response.isStatus());
    }*/
}
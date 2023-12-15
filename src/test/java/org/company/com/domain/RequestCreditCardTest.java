package org.company.com.domain;

import org.company.com.domain.services.RequestCreditCardServiceImpl;
import org.company.com.mock.MockTableRequestCreditCard;
import org.company.com.domain.Response.ResponseRequestCreditCardStatus;
import org.company.com.domain.repository.RequestCreditCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class RequestCreditCardTest {

    private RequestCreditCardServiceImpl requestCreditCardService;

    @BeforeEach
    void setUp() {

        MockTableRequestCreditCard mockRequestCreditCardDB = new MockTableRequestCreditCard();
        RequestCreditCardRepository requestCreditCardRepository = new RequestCreditCardRepository(mockRequestCreditCardDB);
        requestCreditCardService = new RequestCreditCardServiceImpl(requestCreditCardRepository);

    }

    @Test
    public void sendRequestSuccessfully() {
        UUID id = UUID.fromString("6888afcb-07e8-4c2e-a9a0-6df02618a6d8");
        String expectedMessage = "Request sent successfully";

        ResponseRequestCreditCardStatus requestCreditCardStatus = requestCreditCardService.checkRequestSent(id);

        String currentMessage = requestCreditCardStatus.getMessage();
        boolean currentRequestSent = requestCreditCardStatus.isRequestSent();


        assertEquals(expectedMessage, currentMessage);
        assertTrue(currentRequestSent);
    }

    @Test

    public void sendRequestFailed() {
        UUID id = UUID.fromString("67a5f5c9-8ae1-4d7e-ab34-2c9aa4ab3af2");
        String expectedMessage = "Error sending request";

        ResponseRequestCreditCardStatus requestCreditCardStatus = requestCreditCardService.checkRequestSent(id);

        String currentMessage = requestCreditCardStatus.getMessage();
        boolean currentRequestSent = requestCreditCardStatus.isRequestSent();

        assertEquals(expectedMessage, currentMessage);
        assertFalse(currentRequestSent);
    }


}

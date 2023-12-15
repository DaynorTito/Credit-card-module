package org.company.com.controllers;

class CreateCreditCardControllerTest {

//
//    Controller<ResponseCreditCard, DataCreateCreditCardRequest> controller;
//
//    @BeforeEach
//    void setUp() {
//        EmailNotificationService emailNotificationService = new EmailNotificationService();
//        CreditCardService creditCardService = new CreditCardService();
//        CreditCardLimit creditCardLimit = new CreditCardLimit();
//        CardNumberGenerator cardNumberGenerator = new CardNumberGenerator();
//        CvvGenerator cvvGenerator = new CvvGenerator();
//        ExpirationDateGenerator expirationDateGenerator = new ExpirationDateGenerator();
//        controller = new CreateCreditCardController(emailNotificationService, creditCardService, creditCardLimit, cardNumberGenerator, cvvGenerator, expirationDateGenerator);
//    }
//
//
//    @Test
//    public void testExceptionWhenRequestNull() {
//        // Arrange
//        DataCreateCreditCardRequest request = null;
//
//        // Act
//        RequestNullException exception = assertThrows(RequestNullException.class, () -> controller.execute(request));
//
//        // Assert
//        assertEquals(RequestNullException.msg, exception.getMessage());
//    }
//
//
//    @Test
//    public void testReturnCreditCardNumber() {
//
//        // Arrange
//        UUID uuid = UUID.randomUUID();
//        Date birthDate = new Date();
//        DataCreateCreditCardRequest request = new DataCreateCreditCardRequest("1000", JobStabilityStatus.STABLE, "David", "Lezama", "test@gmail.test", uuid);
//
//        // Act
//        ResponseCreditCard response = assertDoesNotThrow(() -> controller.execute(request));
//
//        // Assert
//        assertNotNull(response.getCardNumber());
//    }
}
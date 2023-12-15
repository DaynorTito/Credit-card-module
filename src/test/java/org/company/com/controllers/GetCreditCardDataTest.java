package org.company.com.controllers;

public class GetCreditCardDataTest {

//
//    Controller<ResponseCreditCardData, String> controller;
//
//    @BeforeEach
//    void setUp() {
//
//        MockTableCreditCard mockTableCreditCard = new MockTableCreditCard();
//        CreditCardRepository creditCardRepository = new CreditCardRepository(mockTableCreditCard);
//       
//        controller = new GetCreditCardDataController(creditCardRepository);
//    }
//
//    @Test
//    public void testExceptionNotFoundCreditCard() {
//        // Arrange
//        String cardNumber = null;
//
//        // Act
//        RequestNullException exception = assertThrows(RequestNullException.class, () -> controller.execute(cardNumber));
//
//        // Assert
//        assertEquals(RequestNullException.msg, exception.getMessage());
//    }
//
//
//    @Test
//    public void testReturnCreditCardData() {
//        try {
//            ResponseCreditCardData data = controller.execute("123456789");
//            assertNotNull(data);
//        } catch (Exception e) {
//            fail("Should not throw exception");
//        }
//    }
}
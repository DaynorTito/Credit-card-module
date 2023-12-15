package org.company.com.domain.services.creditCardServicesTest;

public class CreditCardServiceTest {
//    @Test
//    public void testGenerateCreditCard() {
//        CardNumberGenerator cardNumberGenerator = new CardNumberGenerator();
//        CvvGenerator cvvGenerator = new CvvGenerator();
//        ExpirationDateGenerator expirationDateGenerator = new ExpirationDateGenerator();
//
//        CreditAccount creditAccount = new CreditAccount("1092833901", 1000.0, 500.0);
//        CreditCardService creditCardService = new CreditCardService();
//
//        User user = new User("John", "Aguilar");
//        UUID userId = UUID.randomUUID();
//        CreditCard creditCard = creditCardService.generateCreditCard(userId, user, creditAccount, String.valueOf(cardNumberGenerator.getCardNumber()), cvvGenerator.generateCvv(), expirationDateGenerator.dateGenerator());
//
//        assertNotNull(creditCard);
//        assertEquals(user, creditCard.getUser());
//        assertTrue(String.valueOf(creditCard.getCardNumber()).startsWith("2201"));
//        assertTrue(creditCard.getCvv() >= 100 && creditCard.getCvv() <= 999);
//        LocalDateTime expirationDate = creditCard.getExpirationDate();
//        LocalDate currentDate = LocalDate.now();
//        assertEquals(currentDate.plusYears(4).atStartOfDay().toLocalDate(), expirationDate.toLocalDate());
//
//        assertEquals("1092833901", creditCard.getCreditAccount().getAccountNumber());
//        assertEquals(1000.0, creditCard.getCreditAccount().getAvailableBalance());
//        assertEquals(500.0, creditCard.getCreditAccount().getCurrentDebt());
//    }

}
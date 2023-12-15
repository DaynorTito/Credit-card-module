package org.company.com.domain.services.creditCardServices;

import java.util.Random;

//@Service
public class CardNumberGenerator {
    private long cardNumber;

    public CardNumberGenerator() {
        this.cardNumber = cardNumberCreator();
    }

    private long cardNumberCreator() {
        String[] cardNumberArray = new String[16];
        String[] bankPrefix = {"2", "2", "0", "1"};

        long cardNumber;

        Random random = new Random();
        for (int i = 0; i < cardNumberArray.length; i++) {
            if (i > 3) {
                cardNumberArray[i] = String.valueOf(random.nextInt(10));
            } else {
                cardNumberArray[i] = bankPrefix[i];
            }
        }
        String cardNumberStr = String.join("", cardNumberArray);
        cardNumber = Long.valueOf(cardNumberStr);

        return cardNumber;
    }

    public long getCardNumber() {
        return cardNumber;
    }

}
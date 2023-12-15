package org.company.com.domain.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CardDataTemplateEmail {

    private static final String PATTERN_TO_FORMAT_DATE = "yyyy-MM-dd";

    private final String name;
    private final String cardNumber;
    private final double creditLimit;
    private final LocalDateTime expirationDate;

    public CardDataTemplateEmail(String name, String cardNumber, double creditLimit, LocalDateTime expirationDate) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.creditLimit = creditLimit;
        this.expirationDate = expirationDate;
    }

    public String getMessageHTML() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_TO_FORMAT_DATE);
        String fechaHoraString = expirationDate.format(formatter);
        String credit = String.format("%.2f", this.creditLimit);
        return "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f2f2f2;}\n" +
                "        .container {\n" +
                "            background-color: #fff;\n" +
                "            border-radius: 5px;\n" +
                "            padding: 20px;\n" +
                "            margin: 20px;\n" +
                "            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);}\n" +
                "        .header {\n" +
                "            background-color: #007bff;\n" +
                "            color: #fff;\n" +
                "            padding: 10px;\n" +
                "            text-align: center;\n" +
                "            font-size: 24px;}\n" +
                "        .content {\n" +
                "            padding: 20px;}\n" +
                "        .message {\n" +
                "            font-size: 15px; color: #000;}\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\"> Credit card details</div>\n" +
                "        <div class=\"content\">\n" +
                "            <p class=\"message\">Dear " + this.name + ",</p>\n" +
                "            <p class=\"message\">As you are aware, your credit card application was successfully approved.</p>\n" +
                "            <p class=\"message\">Below are the details of your card:</p>\n" +
                "            <ul>" +
                "                <li><strong>Card Number:</strong> " + this.cardNumber + "</li>" +
                "                <li><strong>Approved Limit:</strong> $" + this.creditLimit + "</li>" +
                "                <li><strong>Expiration Date:</strong> " + fechaHoraString + "</li>" +
                "            </ul>" +
                "            <p class=\"message\">Remember to keep these data very well, in case you lose them, " +
                "request them again to the bank.</p>\n" +
                "            <p class=\"message\">We hope you enjoy the benefits of your new credit card!</p>\n" +
                "<p class=\"message\">Thank you for choosing us as your financial institution. If you have any questions," +
                " please do not hesitate to contact us at <a href=\"mailto:creditcardmodule@gmail.com\">creditcardmodule@gmail.com</a>.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
    }

}

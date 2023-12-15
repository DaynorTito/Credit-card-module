package org.company.com.domain.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MinimumPaymentNotification {

    private final String name;
    private static final String PATTERN_TO_FORMAT_DATE = "yyyy-MM-dd";
    private double minimumMonthlyPayment;
    private LocalDateTime deadline;

    public MinimumPaymentNotification(String name, double minimumMonthlyPayment, LocalDateTime deadline) {
        this.name = name;
        this.minimumMonthlyPayment = minimumMonthlyPayment;
        this.deadline = deadline;
    }

    public String getMessageHTML() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_TO_FORMAT_DATE);
        String dueDateString = deadline.format(formatter);
        String minimumPaymentString = String.format("%.2f", minimumMonthlyPayment);

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
                "            background-color: #ffac46;\n" +
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
                "        <div class=\"header\"> Payment Reminder</div>\n" +
                "        <div class=\"content\">\n" +
                "            <p class=\"message\">Dear " + name + ",</p>\n" +
                "            <p class=\"message\">This is a friendly reminder about your credit card's minimum payment.</p>\n" +
                "            <p class=\"message\">Below are the details:</p>\n" +
                "            <ul>" +
                "                <li><strong>Minimum Payment Amount:</strong> $" + minimumPaymentString + "</li>" +
                "                <li><strong>Due Date:</strong> " + dueDateString + "</li>" +
                "            </ul>" +
                "            <p class=\"message\">Please ensure to make the minimum payment by the due date to avoid any additional charges.</p>\n" +
                "            <p class \"message\">If you have any questions or concerns, please don't hesitate to contact us at " +
                "<a href=\"mailto:creditcardmodule@gmail.com\">creditcardmodule@gmail.com</a>.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
    }
}

package org.company.com.domain.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AutomaticPaymentFailureEmailTemplate {
    private static final String PATTERN_TO_FORMAT_DATE = "yyyy-MM-dd";
    private final String name;
    private final double amount;
    private final LocalDateTime dateFailure;

    public AutomaticPaymentFailureEmailTemplate(String name, double amount, LocalDateTime dateFailure) {
        this.name = name;
        this.amount = amount;
        this.dateFailure = dateFailure;
    }

    public String getMessageHTML() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_TO_FORMAT_DATE);
        String fechaHoraString = dateFailure.format(formatter);
        String amount = String.format("%.2f", this.amount);
        return "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: 'Arial', sans-serif;\n" +
                "            background-color: #f2f2f2;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        .container {\n" +
                "            background-color: #fff;\n" +
                "            border-radius: 8px;\n" +
                "            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n" +
                "            margin: 20px;\n" +
                "            padding: 20px;\n" +
                "        }\n" +
                "        .header {\n" +
                "            background-color: #ff4d4d;\n" +
                "            color: #fff;\n" +
                "            padding: 15px;\n" +
                "            text-align: center;\n" +
                "            font-size: 24px;\n" +
                "            border-radius: 5px;\n" +
                "        }\n" +
                "        .content {\n" +
                "            padding: 20px;\n" +
                "            font-size: 16px;\n" +
                "        }\n" +
                "        .message {\n" +
                "            line-height: 1.6;\n" +
                "            margin-bottom: 15px;\n" +
                "        }\n" +
                "        ul {\n" +
                "            list-style-type: disc;\n" +
                "            padding-left: 20px;\n" +
                "            margin-top: 15px;\n" +
                "        }\n" +
                "        ul li {\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "        a {\n" +
                "            color: #007bff;\n" +
                "            text-decoration: none;\n" +
                "            font-weight: bold;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\"> AUTOMATIC PAYMENT FAILED</div>\n" +
                "        <div class=\"content\">\n" +
                "            <p class=\"message\">Dear <strong>" + name + "</strong>,</p>\n" +
                "            <p class=\"message\">Unfortunately, we were unable to process your scheduled automatic payment. Please review the details of your payment and ensure there are sufficient funds available in your account.</p>\n" +
                "            \n" +
                "            <p class=\"message\">The date when the payment failed was <strong>" + fechaHoraString + "</strong>, for an amount of <strong>$" + amount + "</strong>.</p>\n" +
                "            <p class=\"message\">Please take the necessary steps to address this issue promptly. Here are some suggestions:</p>\n" +
                "            <ul>\n" +
                "                <li>Verify that your card information is correct and up to date.</li>\n" +
                "                <li>Ensure that there are sufficient funds in your account to cover the payment.</li>\n" +
                "                <li>Check if your card has expired, and if so, update the information.</li>\n" +
                "                <li>If you continue to experience issues, please contact our customer service for additional assistance.</li>\n" +
                "            </ul>\n" +
                "            <p class=\"message\">If you have any questions or need assistance, feel free to <a href=\"mailto:creditcardmodule@gmail.com\">contact us</a>.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>";
    }
}

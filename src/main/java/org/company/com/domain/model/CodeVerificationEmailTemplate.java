package org.company.com.domain.model;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CodeVerificationEmailTemplate {
    private final String code;

    public String getMessageHTML() {

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
                "        <div class=\"header\"> Verification Code for Login</div>\n" +
                "        <div class=\"content\">\n" +
                "            <p class=\"message\">Your verification code for login is: <strong>" + code + "</strong></p>\n" +
                "            <p class=\"message\">Please enter this code to complete the login process.</p>\n" +
                "            <p class=\"message\">If you did not request this code, please ignore this email.</p>\n" +
                "            <p class=\"message\">If you have any questions or need assistance, contact us at <a href=\"mailto:support@example.com\">support@example.com</a>.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
    }

}

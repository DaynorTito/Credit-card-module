package org.company.com.domain.model;

public class RequestStatusEmailsModel {
    private final String name;
    private final  RequestCreditCardStatus state;
    private String subject;
    private String message;
    private String color;


    public RequestStatusEmailsModel(String name, RequestCreditCardStatus state) {
        this.name = name;
        this.state = state;
    }

    public String getMailHTML() {
        if (state == RequestCreditCardStatus.APPROVED) {
            color = "#28a745";
            subject = "Credit card application approved";
            message = "We are pleased to inform you that your credit card application has been successfully approved";
        } else if (state == RequestCreditCardStatus.REJECTED) {
            color = "#dc3545";
            subject = "Credit card application denied";
            message = "We regret to inform you that your credit card application was declined";
        } else {
            color = "#ffc107";
            subject = "Application in process";
            message = "The bank did not finish evaluating your application";
        }
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
                "            background-color: " + color + ";\n" +
                "            color: #fff;\n" +
                "            padding: 10px;\n" +
                "            text-align: center;\n" +
                "            font-size: 24px;}\n" +
                "        .content {\n" +
                "            padding: 20px;}\n" +
                "        .message {\n" +
                "            font-size: 18px; color: #000;}\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">" + subject + "!</div>\n" +
                "        <div class=\"content\">\n" +
                "            <p class=\"message\">Dear " + name + "</p>\n" +
                "            <p class=\"message\">" + message + ".</p>\n" +
                "            <p class=\"message\">Thank you for choosing us as your financial institution. If you have any questions, please do not hesitate to contact us.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
    }
}

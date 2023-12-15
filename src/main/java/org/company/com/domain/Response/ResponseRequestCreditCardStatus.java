package org.company.com.domain.Response;

import lombok.Data;

@Data
public class ResponseRequestCreditCardStatus {

    private final String message;
    private final boolean status;
    private final boolean isRequestSent;

    public ResponseRequestCreditCardStatus(String message, boolean status, boolean isRequestSent) {
        this.message = message;
        this.status = status;
        this.isRequestSent = isRequestSent;
    }
}

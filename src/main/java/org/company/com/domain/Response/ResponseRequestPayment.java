package org.company.com.domain.Response;

import java.util.List;

import org.company.com.domain.model.payment.Payment;

public class ResponseRequestPayment {
  private final List<Payment> result;
  private final boolean status;
  private final boolean isRequestSent;

  public ResponseRequestPayment(List<Payment> result, boolean status, boolean isRequestSent) {
    this.result = result;
    this.status = status;
    this.isRequestSent = isRequestSent;
  }
}

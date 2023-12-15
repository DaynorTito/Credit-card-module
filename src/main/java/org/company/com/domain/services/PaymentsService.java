package org.company.com.domain.services;

import org.company.com.domain.repository.PaymentsRepository;

public class PaymentsService {

    private final PaymentsRepository paymentsRepository;

    public PaymentsService(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }
//
//  public ResponseRequestPayment checkPaymentByIdCardRequest(UUID id) {
//    List<Payment> requestPayment = paymentsRepository.findAllPaymentByCardId(id);
//    if (!requestPayment.isEmpty()) {
//      return new ResponseRequestPayment(requestPayment, true, true);
//    }
//    return new ResponseRequestPayment(requestPayment, false, false);
//  }
//
//  public ResponseRequestPayment checkAllPaymentRequest() {
//    List<Payment> requestPayment = paymentsRepository.findAllPayment();
//    if (!requestPayment.isEmpty()) {
//      return new ResponseRequestPayment(requestPayment, true, true);
//    }
//    return new ResponseRequestPayment(requestPayment, false, false);
//  }
//
//  public ResponseRequestPayment checkAllAccountPaymentRequest(UUID id) {
//    List<Payment> requestPayment = paymentsRepository.findAllPaymentByAccount(id);
//    if (!requestPayment.isEmpty()) {
//      return new ResponseRequestPayment(requestPayment, true, true);
//    }
//    return new ResponseRequestPayment(requestPayment, false, false);
//  }
//
//  public ResponseRequestPayment checkAllStatusPaymentRequest(PaymentStatus status) {
//    List<Payment> requestPayment = paymentsRepository.findAllPaymentByStatus(status);
//    if (!requestPayment.isEmpty()) {
//      return new ResponseRequestPayment(requestPayment, true, true);
//    }
//    return new ResponseRequestPayment(requestPayment, false, false);
//  }
}

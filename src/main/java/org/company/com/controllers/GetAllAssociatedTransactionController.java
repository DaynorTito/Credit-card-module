package org.company.com.controllers;

import lombok.AllArgsConstructor;
import org.company.com.controllers.exceptions.ControllerException;
import org.company.com.controllers.exceptions.RequestNullException;
import org.company.com.domain.Response.ResponseRequestTransaction;
import org.company.com.domain.model.Controller;
import org.company.com.domain.services.TransactionHistoryService;

import java.util.UUID;

@AllArgsConstructor
public class GetAllAssociatedTransactionController implements Controller<ResponseRequestTransaction, UUID> {
    TransactionHistoryService transactionHistoryService;

    @Override
    public ResponseRequestTransaction execute(UUID request) throws ControllerException {
        if (request == null) throw new RequestNullException();
        return transactionHistoryService.checkTransactionByIdCardRequest(request);
    }
}

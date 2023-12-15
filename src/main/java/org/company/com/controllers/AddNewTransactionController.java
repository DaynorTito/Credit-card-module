package org.company.com.controllers;

import org.company.com.controllers.exceptions.RequestNullException;
import org.company.com.domain.model.Controller;
import org.company.com.domain.model.Transaction;
import org.company.com.domain.services.NewTransactionService;

import java.util.UUID;

public class AddNewTransactionController implements Controller<UUID, Transaction> {


    NewTransactionService newTransactionService;
   

    @Override
    public UUID execute(Transaction request) throws Exception {
        if (request == null) throw new RequestNullException();
        return newTransactionService.addTransaction(request);
    }
}

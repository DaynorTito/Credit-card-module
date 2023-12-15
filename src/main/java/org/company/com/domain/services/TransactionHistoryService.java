package org.company.com.domain.services;

import org.company.com.domain.Response.ResponseRequestTransaction;
import org.company.com.domain.model.Transaction;
import org.company.com.domain.repository.TransactionHistoryRepository;

import java.util.List;
import java.util.UUID;

public class TransactionHistoryService {

    private final TransactionHistoryRepository transactionHistoryRepository;

    public TransactionHistoryService(TransactionHistoryRepository transactionHistoryRepository) {
        this.transactionHistoryRepository = transactionHistoryRepository;
    }


    public ResponseRequestTransaction checkTransactionByIdCardRequest(UUID id) {
        List<Transaction> requestTransaction = transactionHistoryRepository.findAllTransactionByCardId(id);
        if (!requestTransaction.isEmpty()) {
            return new ResponseRequestTransaction(requestTransaction, true, true);
        }
        return new ResponseRequestTransaction(requestTransaction, false, false);
    }

    public ResponseRequestTransaction checkAllTransactionRequest() {
        List<Transaction> requestTransaction = transactionHistoryRepository.findAllTransaction();
        if (!requestTransaction.isEmpty()) {
            return new ResponseRequestTransaction(requestTransaction, true, true);
        }
        return new ResponseRequestTransaction(requestTransaction, false, false);
    }

}

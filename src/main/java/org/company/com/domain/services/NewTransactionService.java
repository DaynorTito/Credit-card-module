package org.company.com.domain.services;

import lombok.AllArgsConstructor;
import org.company.com.domain.model.Transaction;
import org.company.com.domain.repository.TransactionHistoryRepository;

import java.util.UUID;

@AllArgsConstructor
public class NewTransactionService {
    TransactionHistoryRepository transactionHistoryRepository;

    public UUID addTransaction(Transaction newTransaction) throws Exception {
        return transactionHistoryRepository.addTransaction(newTransaction);
    }

}

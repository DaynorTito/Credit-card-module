package org.company.com.domain.repository;

import org.company.com.domain.model.Transaction;
import org.company.com.mock.MockTableTransaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class TransactionHistoryRepository {

    private final MockTableTransaction mockTransactionDB;

    public TransactionHistoryRepository(MockTableTransaction mockTransactionDB) {
        this.mockTransactionDB = mockTransactionDB;
    }

    public Optional<Transaction> findTransactionById(UUID id) {
        Transaction filterTransaction = mockTransactionDB.getCollection().stream().filter(transaction -> transaction.getId().equals(id)).findFirst().orElse(null);

        return Optional.ofNullable(filterTransaction);
    }

    public List<Transaction> findAllTransactionByCardId(UUID id) {
        return mockTransactionDB.getCollection().stream().filter(transaction -> transaction.getCreditCard().equals(id)).collect(Collectors.toList());
    }

    public List<Transaction> findAllTransaction() {
        return mockTransactionDB.getCollection();
    }

    public List<Transaction> findAllTransactionByDate(LocalDateTime date) {
        return mockTransactionDB.getCollection().stream().filter(transaction -> transaction.getCreatedAt().equals(date)).collect(Collectors.toList());
    }


    public UUID addTransaction(Transaction transaction) {
        return mockTransactionDB.insertOne(transaction);
    }

}

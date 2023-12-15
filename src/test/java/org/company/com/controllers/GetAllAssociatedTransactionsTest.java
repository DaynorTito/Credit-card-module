package org.company.com.controllers;

import org.company.com.controllers.exceptions.RequestNullException;
import org.company.com.domain.Response.ResponseRequestTransaction;
import org.company.com.domain.model.Transaction;
import org.company.com.domain.services.TransactionHistoryService;
import org.company.com.mock.MockTableTransaction;
import org.company.com.domain.model.Controller;
import org.company.com.domain.repository.TransactionHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetAllAssociatedTransactionsTest {


    Controller<ResponseRequestTransaction, UUID> controller;

    @BeforeEach
    void setUp() {

        MockTableTransaction mockTableTransaction = new MockTableTransaction();
        TransactionHistoryRepository transactionHistoryRepository = new TransactionHistoryRepository(mockTableTransaction);
        TransactionHistoryService transactionHistoryService = new TransactionHistoryService(transactionHistoryRepository);
        controller = new GetAllAssociatedTransactionController(transactionHistoryService);
    }

    @Test
    public void testExceptionWhenRequestNull() {
        UUID request = null;

        RequestNullException exception = assertThrows(RequestNullException.class, () -> controller.execute(request));

        assertEquals(RequestNullException.msg, exception.getMessage());
    }


    @Test
    public void testReturnArrayWithTransactionEmpty() {
        UUID id = UUID.fromString("81e9983b-2d9f-4e60-b735-b79982c299db");

        List<Transaction> data = assertDoesNotThrow(() -> controller.execute(id).getResult());

        System.out.println(data);

        assertTrue(data.isEmpty());
    }

    @Test
    public void testReturnArrayWithTransaction() {
        UUID id = UUID.fromString("069ef55d-c6f5-45a8-a4a2-cd318bb90000");

        List<Transaction> data = assertDoesNotThrow(() -> controller.execute(id).getResult());

        for (Transaction transaction : data) {
            System.out.println(transaction.getAmount());
        }

        assertFalse(data.isEmpty());
    }


}
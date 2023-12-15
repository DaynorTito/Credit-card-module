package org.company.com.domain.model;

import org.company.com.mock.MockTable;
import org.company.com.mock.MockTableTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @BeforeEach
    void setUp() {

    }

    /**
     * Test to verify that the id is not null
     */
    @Test
    public void testGetId() {
        Transaction transaction = new Transaction();
        transaction.setId(null);
        assertNull(transaction.getId());
    }

    /**
     * Test to verify that the ID is not null
     * 
     */
    @Test
    public void allTransactionsShouldHaveAnIdValid() {
        MockTable<Transaction> mockTableTransaction = new MockTableTransaction();
        List<Transaction> transactions = mockTableTransaction.getCollection();

        transactions.forEach(transaction -> assertNotNull(transaction.getId()));
    }


    /**
     * Test to verify that the amount is not null and is greater than 0
     */
    @Test
    public void allTransactionMountShouldBePositive() {
        MockTable<Transaction> mockTableTransaction = new MockTableTransaction();
        List<Transaction> transactions = mockTableTransaction.getCollection();

        transactions.forEach(transaction -> assertTrue(transaction.getAmount() > 0));
    }

    /**
     * Test to verify that the credit card is not null
     */
    @Test
    public void allTransactionConceptShouldBeValid() {
        MockTable<Transaction> mockTableTransaction = new MockTableTransaction();
        List<Transaction> transactions = mockTableTransaction.getCollection();

        transactions.forEach(transaction -> assertNotNull(transaction.getConcept()));
    }

    @AfterEach
    void tearDown() {
    }
}
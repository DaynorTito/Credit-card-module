package org.company.com.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.company.com.domain.Response.ResponseRequestTransaction;
import org.company.com.domain.model.Transaction;
import org.company.com.domain.services.TransactionHistoryService;
import org.company.com.mock.MockTableTransaction;
import org.company.com.domain.repository.TransactionHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionHistoryTest {


  private TransactionHistoryService transactionHistoryService;
  private final MockTableTransaction mockTableTransaction = new MockTableTransaction();

  @BeforeEach
  void setUp() {
    TransactionHistoryRepository transactionHistoryRepository = new TransactionHistoryRepository(mockTableTransaction);
    transactionHistoryService = new TransactionHistoryService(transactionHistoryRepository);
  }

  @Test
  public void getAllTransactionByIdCardSuccessfully() {
    List<Transaction> expectedList = new LinkedList<>();
    UUID idTest = UUID.fromString("069ef55d-c6f5-45a8-a4a2-cd318bb90000");
    expectedList.add(mockTableTransaction.getCollection().get(0));
    expectedList.add(mockTableTransaction.getCollection().get(1));

    ResponseRequestTransaction responseRequestTransaction = transactionHistoryService.checkTransactionByIdCardRequest(idTest);

    List<Transaction> currentList = responseRequestTransaction.getResult();
    boolean currentStatus = responseRequestTransaction.isStatus();

    assertEquals(expectedList, currentList);
    assertTrue(currentStatus);
  }

  @Test
  public void getAllTransactionEmpty() {
    List<Transaction> expectedList = new ArrayList<>();
    UUID idTest = UUID.randomUUID();

    ResponseRequestTransaction responseRequestTransaction = transactionHistoryService.checkTransactionByIdCardRequest(idTest);

    List<Transaction> currentList = responseRequestTransaction.getResult();
    boolean currentStatus = responseRequestTransaction.isStatus();

    assertEquals(expectedList, currentList);
    assertFalse(currentStatus);
  }

  @Test
  public void getAllTransaction() {
    List<Transaction> expectedList = mockTableTransaction.getCollection();

    ResponseRequestTransaction responseRequestTransaction = transactionHistoryService.checkAllTransactionRequest();

    List<Transaction> currentList = responseRequestTransaction.getResult();
    boolean currentStatus = responseRequestTransaction.isStatus();

    assertEquals(expectedList, currentList);
    assertTrue(currentStatus);
  }
}

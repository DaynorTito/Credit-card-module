package org.company.com.domain.Response;


import java.util.List;

import lombok.Data;
import org.company.com.domain.model.Transaction;

@Data
public class ResponseRequestTransaction {
  private final List<Transaction> result;
  private final boolean status;
  private final boolean isRequestSent;

  public ResponseRequestTransaction(List<Transaction> result, boolean status, boolean isRequestSent) {
    this.result = result;
    this.status = status;
    this.isRequestSent = isRequestSent;
  }
}

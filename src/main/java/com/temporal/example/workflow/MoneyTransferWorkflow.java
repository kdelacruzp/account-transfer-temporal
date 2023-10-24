package com.temporal.example.workflow;

import com.temporal.example.model.AccountTransferResponse;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface MoneyTransferWorkflow {

  @WorkflowMethod
  AccountTransferResponse transfer(String fromAccountId,
                                   String fromCurrencyAccount,
                                   String toAccountId,
                                   String toCurrencyAccount,
                                   String reference,
                                   double amount);
}

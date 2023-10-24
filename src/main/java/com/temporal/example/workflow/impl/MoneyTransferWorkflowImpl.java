package com.temporal.example.workflow.impl;

import com.temporal.example.activities.AccountActivity;
import com.temporal.example.model.AccountTransferResponse;
import com.temporal.example.workflow.MoneyTransferWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MoneyTransferWorkflowImpl implements MoneyTransferWorkflow {

  private static final String WITHDRAW = "Withdraw";
  // RetryOptions specify how to automatically handle retries when Activities fail.
  private final RetryOptions retryoptions = RetryOptions.newBuilder()
      .setInitialInterval(Duration.ofSeconds(1))
      .setMaximumInterval(Duration.ofSeconds(100))
      .setBackoffCoefficient(2)
      .setMaximumAttempts(2)
      .build();
  private final ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
      // Timeout options specify when to automatically timeout Activities if the process is taking too long.
      .setStartToCloseTimeout(Duration.ofSeconds(90))
      // Optionally provide customized RetryOptions.
      // Temporal retries failures by default, this is simply an example.
      .setRetryOptions(retryoptions)
      .build();
  // ActivityStubs enable calls to methods as if the Activity object is local, but actually perform an RPC.
  private final Map<String, ActivityOptions> perActivityMethodOptions = new HashMap<String, ActivityOptions>(){{
    put(WITHDRAW, ActivityOptions.newBuilder().setHeartbeatTimeout(Duration.ofSeconds(5)).build());
  }};

  private final AccountActivity account = Workflow.newActivityStub(AccountActivity.class, defaultActivityOptions, perActivityMethodOptions);

  // The transfer method is the entry point to the Workflow.
  // Activity method executions can be orchestrated here or from within other Activity methods.
  @Override
  public AccountTransferResponse transfer(String fromAccountId,
                                          String fromCurrencyAccount,
                                          String toAccountId,
                                          String toCurrencyAccount,
                                          String reference,
                                          double amount) {

    /**
     * If there were other Activity methods they would be orchestrated here or from within other Activities.
     * This is a blocking call that returns only after the activity has completed.
     */
    Double amountTransfer = account.calculateAmounExchangeRate(fromCurrencyAccount, toCurrencyAccount, amount);

    account.withdraw(fromAccountId, reference, amountTransfer);

    account.deposit(toAccountId, reference, amountTransfer);

    return AccountTransferResponse.builder()
        .transferId(UUID.randomUUID().toString())
        .fromAccountId(fromAccountId)
        .toAccountId(toAccountId)
        .amount(amountTransfer)
        .build();
  }
}

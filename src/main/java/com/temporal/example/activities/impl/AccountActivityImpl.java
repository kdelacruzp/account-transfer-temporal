package com.temporal.example.activities.impl;

import com.temporal.example.activities.AccountActivity;
import com.temporal.example.client.WithdrawAccountApi;
import com.temporal.example.model.WithdrawAccountRequest;
import lombok.extern.slf4j.Slf4j;

import static com.temporal.example.util.Constant.EXCHANGE_RATE;

@Slf4j
public class AccountActivityImpl implements AccountActivity {

  @Override
  public void withdraw(String accountId, String reference, double amount) {
    log.info(
        "\nWithdrawing {} from account {} ReferenceId: {}\n", amount, accountId, reference
    );

    if (accountId.equals("000000")) throw new NullPointerException();
  }

  @Override
  public void deposit(String accountId, String referenceId, double amount) {

    log.info(
        "\nDepositing {} into account {} ReferenceId: {}\n",
        amount, accountId, referenceId
    );

    try {
      if(amount < 0) {
          Thread.sleep(10000);
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Double calculateAmounExchangeRate(String fromCurrency, String toCurrency, double amount) {
    log.info(
        "\nCalculating amount exchange rate from currency {} to Currency {} Amount: {}\n",
        fromCurrency, toCurrency, amount
    );
    if (!fromCurrency.equals(toCurrency)) {
      return fromCurrency.equals("USD") ? amount * EXCHANGE_RATE : amount / EXCHANGE_RATE;
    }
    return amount;
  }
}

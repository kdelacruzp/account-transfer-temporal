package com.temporal.example.activities;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;


@ActivityInterface
public interface AccountActivity {

  @ActivityMethod
  void withdraw(String accountId, String referenceId, double amount);

  @ActivityMethod
  void deposit(String accountId, String referenceId, double amount);

  @ActivityMethod
  Double calculateAmounExchangeRate(String fromCurrency, String toCurrency, double amount);
}

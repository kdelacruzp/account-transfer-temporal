package com.temporal.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountTransferDto {

  String fromAccountId;
  String fromCurrencyAccount;
  String toAccountId;
  String toCurrencyAccount;
  String reference;
  Double amount;
}

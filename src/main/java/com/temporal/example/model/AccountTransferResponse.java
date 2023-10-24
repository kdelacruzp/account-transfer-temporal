package com.temporal.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountTransferResponse {

  String transferId;
  String fromAccountId;
  String toAccountId;
  Double amount;
}

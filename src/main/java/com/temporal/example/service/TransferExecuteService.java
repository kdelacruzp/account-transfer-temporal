package com.temporal.example.service;

import com.temporal.example.model.AccountTransferDto;
import com.temporal.example.model.AccountTransferResponse;

public interface TransferExecuteService {

  AccountTransferResponse executeTransfer(AccountTransferDto accountTransferDTO);

  AccountTransferResponse executeTransferError(AccountTransferDto accountTransferDTO);
}

package com.temporal.example.resource;

import com.temporal.example.client.WithdrawAccountApi;
import com.temporal.example.model.AccountTransferDto;
import com.temporal.example.model.AccountTransferResponse;
import com.temporal.example.service.TransferExecuteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/transfer")
public class TransferResource {

  @Inject
  TransferExecuteService transferExecute;

  @POST
  @Path("/execute")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public AccountTransferResponse executeTransfer(AccountTransferDto accountTransferDTO) {
    return transferExecute.executeTransfer(accountTransferDTO);
  }
}

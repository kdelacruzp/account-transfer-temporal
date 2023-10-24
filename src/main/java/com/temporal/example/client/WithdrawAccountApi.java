package com.temporal.example.client;

import com.temporal.example.model.WithdrawAccountRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/operation/account")
@RegisterRestClient(configKey="extensions-api")
public interface WithdrawAccountApi {

  @POST
  @Path("/withdraw")
  Response executeWithdraw(WithdrawAccountRequest withdrawAccountRequest);
}

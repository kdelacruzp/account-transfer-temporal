package com.temporal.example.service.impl;

import com.temporal.example.model.AccountTransferDto;
import com.temporal.example.model.AccountTransferResponse;
import com.temporal.example.service.TransferExecuteService;
import com.temporal.example.util.Constant;
import com.temporal.example.workflow.MoneyTransferWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;

@ApplicationScoped
public class TransferExecuteServiceImpl implements TransferExecuteService {
  @Override
  public AccountTransferResponse executeTransfer(AccountTransferDto accountTransferDTO) {

    // This gRPC stubs wrapper talks to the local docker instance of the Temporal service.
    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

    // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
    WorkflowClient client = WorkflowClient.newInstance(service);

    // Define our workflow unique id
    final String WORKFLOW_ID = "Transfer_Money";

    /*
     * Set Workflow options such as WorkflowId and Task Queue so the worker knows where to list and which workflows to execute.
     */
    WorkflowOptions options = WorkflowOptions.newBuilder()
        .setWorkflowExecutionTimeout(Duration.ofSeconds(8))
        .setWorkflowId(WORKFLOW_ID)
        .setTaskQueue(Constant.TASK_QUEUE)
        .build();

    // Create the workflow client stub. It is used to start our workflow execution.
    MoneyTransferWorkflow workflow = client.newWorkflowStub(MoneyTransferWorkflow.class, options);

    /*
     * Execute our workflow and wait for it to complete. The call to our getGreeting method is
     * synchronous.
     *
     * Replace the parameter "World" in the call to getGreeting() with your name.
     */
    return workflow.transfer(accountTransferDTO.getFromAccountId(),
        accountTransferDTO.getFromCurrencyAccount(),
        accountTransferDTO.getFromAccountId(),
        accountTransferDTO.getToCurrencyAccount(),
        accountTransferDTO.getReference(),
        accountTransferDTO.getAmount());
  }

  @Override
  public AccountTransferResponse executeTransferError(AccountTransferDto accountTransferDTO) {
    // This gRPC stubs wrapper talks to the local docker instance of the Temporal service.
    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

    // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
    WorkflowClient client = WorkflowClient.newInstance(service);

    // Define our workflow unique id
    final String WORKFLOW_ID = "Transfer_Money";

    /*
     * Set Workflow options such as WorkflowId and Task Queue so the worker knows where to list and which workflows to execute.
     */
    WorkflowOptions options = WorkflowOptions.newBuilder()
        .setWorkflowExecutionTimeout(Duration.ofSeconds(8))
        .setWorkflowId(WORKFLOW_ID)
        .setTaskQueue(Constant.TASK_QUEUE)
        .build();

    // Create the workflow client stub. It is used to start our workflow execution.
    MoneyTransferWorkflow workflow = client.newWorkflowStub(MoneyTransferWorkflow.class, options);

    /*
     * Execute our workflow and wait for it to complete. The call to our getGreeting method is
     * synchronous.
     *
     * Replace the parameter "World" in the call to getGreeting() with your name.
     */
    return workflow.transfer(accountTransferDTO.getFromAccountId(),
        accountTransferDTO.getFromCurrencyAccount(),
        accountTransferDTO.getFromAccountId(),
        accountTransferDTO.getToCurrencyAccount(),
        accountTransferDTO.getReference(),
        accountTransferDTO.getAmount());
  }
}

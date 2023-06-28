package com.example.mailservice.storagequeue;

import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import com.azure.messaging.servicebus.models.ServiceBusReceiveMode;
import com.example.mailservice.dto.MailRequestDTO;
import com.example.mailservice.service.MailService;

@Service
public class StorageQueueService {
  private final static String queueName = "mail-queue";
  private final static String connectionString =
      "Endpoint=sb://mail-svc.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=tT62yQYKJOFPt+NMVjJ80TZzxlmo7AEFV+ASbOTRxjU=";
  @Autowired
  private MailService mailService;

  public void dequeueMessage() {
    Consumer<ServiceBusReceivedMessageContext> processMessage = context -> {
      final ServiceBusReceivedMessage message = context.getMessage();
      System.out.printf("handler processing message. Session: %s, Sequence #: %s. Contents: %s%n",
          message.getMessageId(), message.getSequenceNumber(), message.getBody());
      MailRequestDTO mail = message.getBody().toObject(MailRequestDTO.class);
      mailService.sendSimpleMessage(mail);
    };

    Consumer<ServiceBusErrorContext> processError = errorContext -> {
      System.err.println("Error occurred while receiving message: " + errorContext.getException());
    };

    ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
        .connectionString(connectionString).processor().queueName(queueName)
        .receiveMode(ServiceBusReceiveMode.RECEIVE_AND_DELETE).processMessage(processMessage)
        .processError(processError).disableAutoComplete().buildProcessorClient();

    processorClient.start();
  }
}

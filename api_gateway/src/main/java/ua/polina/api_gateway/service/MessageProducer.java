package ua.polina.api_gateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ua.polina.person.api.messaging.command.CreatePerson;

@RequiredArgsConstructor
@Slf4j
@Component
public class MessageProducer implements JavaDelegate {
    private final KafkaTemplate<String, CreatePerson> kafkaTemplate;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Starting business process");
        CreatePerson command = (CreatePerson) delegateExecution.getVariable("messageToSend");
        delegateExecution.setVariable("receivedMessage", "");
        kafkaTemplate.send(command.queue(), command);;
    }

}

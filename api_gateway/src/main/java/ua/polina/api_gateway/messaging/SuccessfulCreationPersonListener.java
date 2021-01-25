package ua.polina.api_gateway.messaging;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ua.polina.person.api.dto.PersonDto;
import ua.polina.person.api.messaging.event.SuccessfulCreationPersonEvent;

@Slf4j
@Service
@KafkaListener(topics = "response", containerFactory = "responseDtoConcurrentKafkaListenerContainerFactory")
@AllArgsConstructor
public class SuccessfulCreationPersonListener {
    @Autowired
    private final RuntimeService runtimeService;

    @KafkaHandler
    public void listenGroupPerson(SuccessfulCreationPersonEvent command) {
        log.info("Receiving data: ' " + command);
        PersonDto personDto = command.payload();
        log.info("Person successfully received: " + personDto);

        runtimeService.createMessageCorrelation("receivedMessage")
                .processInstanceId(runtimeService.createProcessInstanceQuery().list().get(0).getId())
                .setVariable("receivedMessage", personDto.toString())
                .correlate();

}
}

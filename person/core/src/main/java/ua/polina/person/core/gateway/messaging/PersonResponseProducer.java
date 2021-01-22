package ua.polina.person.core.gateway.messaging;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ua.polina.person.api.dto.PersonDto;
import ua.polina.person.api.messaging.event.SuccessfulCreationPersonEvent;

@Service
@Slf4j
@AllArgsConstructor
public class PersonResponseProducer {
    private final KafkaTemplate<String, SuccessfulCreationPersonEvent> responseDtoKafkaTemplate;


    public void sendMessage(PersonDto dto){
        log.info("Sending response: " + dto);
        SuccessfulCreationPersonEvent event = new SuccessfulCreationPersonEvent(dto);
        responseDtoKafkaTemplate.send(event.topic(), event);
    }
}

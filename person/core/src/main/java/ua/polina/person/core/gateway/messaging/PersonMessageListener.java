package ua.polina.person.core.gateway.messaging;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.polina.person.api.dto.PersonDto;
import ua.polina.person.api.messaging.command.CreatePerson;
import ua.polina.person.core.service.PersonService;

@Slf4j
@Service
@AllArgsConstructor
@KafkaListener(topics = CreatePerson.QUEUE, groupId = "person", containerFactory = "personDtoConcurrentKafkaListenerContainerFactory")
public class PersonMessageListener {
    private final PersonService personService;
    private final PersonResponseProducer personResponseProducer;

    @KafkaHandler
    @Transactional
    public void listenGroupPerson(CreatePerson command) {
        log.info("Sending data: ' " + command);
        try {
            PersonDto personDto = command.payload();
            PersonDto person = personService.createPerson(personDto);
            log.info("Person successfully saved: " + person);
            personResponseProducer.sendMessage(personDto);
        } catch (Exception e) {
            log.info("Something went wrong");
            personResponseProducer.sendMessage(new PersonDto());
        }
    }
}

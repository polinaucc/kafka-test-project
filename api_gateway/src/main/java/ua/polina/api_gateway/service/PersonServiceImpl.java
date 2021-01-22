package ua.polina.api_gateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ua.polina.person.api.dto.PersonDto;
import ua.polina.person.api.messaging.command.CreatePerson;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final KafkaTemplate<String, CreatePerson> kafkaTemplate;
    @Override
    public void createPerson(PersonDto dto) {
        CreatePerson command = new CreatePerson(dto);
        kafkaTemplate.send(command.queue(), command);
    }
}

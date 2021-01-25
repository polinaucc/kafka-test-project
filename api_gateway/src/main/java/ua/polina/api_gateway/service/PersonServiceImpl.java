package ua.polina.api_gateway.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ua.polina.person.api.dto.PersonDto;
import ua.polina.person.api.messaging.command.CreatePerson;

import java.util.HashMap;
import java.util.Map;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final KafkaTemplate<String, CreatePerson> kafkaTemplate;
    @Autowired
    private RuntimeService runtimeService;

    @Override
    public void createPerson(PersonDto dto) {
        CreatePerson command = new CreatePerson(dto);
        Map<String, Object> variablesForCamunda = new HashMap<>();
        variablesForCamunda.put("messageToSend", command);
        variablesForCamunda.put("receivedMessage", "");
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("send-message-process", "send-message-process", variablesForCamunda);
    }
}

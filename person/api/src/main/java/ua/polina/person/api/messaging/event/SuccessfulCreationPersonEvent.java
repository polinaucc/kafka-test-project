package ua.polina.person.api.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.polina.message_api.command.Event;
import ua.polina.person.api.dto.PersonDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessfulCreationPersonEvent implements Event<PersonDto> {
    public static final String TOPIC = "response";

    private PersonDto personDto;

    @Override
    public PersonDto payload() {
        return personDto;
    }

    @Override
    public String topic() {
        return TOPIC;
    }
}

package ua.polina.person.api.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.polina.message_api.command.Event;
import ua.polina.person.api.dto.PersonDto;

import java.io.Serializable;

/**
 * Response when a person is successfully saved or not. If person isn't saved
 * personDto has default (null) fields' values.
 *
 * @author Polina Serhiienko
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessfulCreationPersonEvent implements Event<PersonDto>, Serializable {
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

package ua.polina.person.api.messaging.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.polina.message_api.command.Command;
import ua.polina.person.api.dto.PersonDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePerson implements Command<PersonDto> {
    public static final String QUEUE = "person.create.queue";

    private PersonDto personDto;

    @Override
    public PersonDto payload() {
        return personDto;
    }

    @Override
    public String queue() {
        return QUEUE;
    }
}

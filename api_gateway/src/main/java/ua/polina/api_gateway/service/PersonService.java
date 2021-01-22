package ua.polina.api_gateway.service;

import ua.polina.person.api.dto.PersonDto;

public interface PersonService {
    void createPerson(PersonDto dto);
}

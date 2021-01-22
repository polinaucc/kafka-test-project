package ua.polina.person.core.service;
import ua.polina.person.api.dto.*;

/**
 * Service that manages persons.
 */
public interface PersonService {
    /**
     * Creates new person.
     */
   PersonDto createPerson(PersonDto personDto);
}

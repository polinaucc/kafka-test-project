package ua.polina.person.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.polina.person.api.dto.PersonDto;
import ua.polina.person.core.entity.Person;
import ua.polina.person.core.mapper.PersonMapper;
import ua.polina.person.core.repository.PersonRepository;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper mapper;

    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person person = mapper.personDtoToPerson(personDto);
        person = personRepository.save(person);
        return mapper.personToPersonDto(person);
    }
}

package ua.polina.person.core.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.polina.person.api.dto.PersonDto;
import ua.polina.person.core.PersonApplication;
import ua.polina.person.core.entity.Person;
import ua.polina.person.core.mapper.PersonMapper;
import ua.polina.person.core.mapper.PersonMapperImpl;
import ua.polina.person.core.repository.PersonRepository;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(
        classes = {PersonApplication.class, PersonServiceImpl.class, PersonMapperImpl.class})
public class PersonServiceImplTest {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    PersonDto personDto;

    @Before
    public void setUp() {
        personDto = new PersonDto("Filatov", "Andrey", "Stepanovich", "AA111111");
    }

    @Test
    public void createPerson() {
        PersonDto personDtoAfterSaving = personService.createPerson(personDto);
        List<Person> personList = personRepository.findAll();
        Assert.assertEquals(personDto, personDtoAfterSaving);
        Assert.assertEquals(personList.size(), 1);
    }
}
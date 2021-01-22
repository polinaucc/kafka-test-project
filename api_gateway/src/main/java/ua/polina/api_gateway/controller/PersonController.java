package ua.polina.api_gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.polina.api_gateway.service.PersonService;
import ua.polina.person.api.dto.PersonDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createPerson(@Valid @RequestBody PersonDto personDto){
        personService.createPerson(personDto);
    }
}

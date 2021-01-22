package ua.polina.person.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ua.polina.person.api.dto.PersonDto;
import ua.polina.person.core.entity.Person;

public interface PersonMapper {
//    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    default PersonDto personToPersonDto(Person entity){
        return PersonDto.builder()
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .lastName(entity.getLastName())
                .passport(entity.getPassport())
                .build();
    }

    default Person personDtoToPerson(PersonDto dto){
        return Person.builder()
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .passport(dto.getPassport())
                .build();
    }
}

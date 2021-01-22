package ua.polina.person.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.person.core.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}

package com.postech.techchallengefase1.repository;

import com.postech.techchallengefase1.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.OptionalLong;
import java.util.Set;

@Repository
public class PersonRepository {

    private final Set<Person> persons;

    public PersonRepository() {
        persons = new HashSet<>();
    }

    public Boolean save(Person person) {
        person.setId(getNextId());
        return persons.add(person);
    }

    public Long getNextId() {
        OptionalLong maxId = persons.stream()
                .mapToLong(Person::getId)
                .max();
        return maxId.orElse(0) + 1;
    }

}

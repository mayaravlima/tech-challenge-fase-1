package com.postech.techchallengefase1.domain.person.repository;

import com.postech.techchallengefase1.domain.person.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
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

    public Set<Person> getPersons() {
        return persons;
    }

    public Person getPersonById(Long id) {
        return persons.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Boolean deletePersonById(Long id) {
        return persons.removeIf(person -> person.getId().equals(id));
    }

    public Optional<Person> updatePerson(Person person) {
        Optional<Person> personToUpdate = persons.stream()
                .filter(person1 -> person1.getId().equals(person.getId()))
                .findFirst();
        if (personToUpdate.isPresent()) {
            Person p = personToUpdate.get();
            persons.remove(p);
            p.setName(person.getName());
            p.setDateOfBirth(person.getDateOfBirth());
            p.setGender(person.getGender());
            p.setRelationship(person.getRelationship());
            persons.add(p);
        }
        return personToUpdate;
    }

}

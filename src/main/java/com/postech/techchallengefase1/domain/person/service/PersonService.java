package com.postech.techchallengefase1.domain.person.service;

import com.postech.techchallengefase1.domain.exception.ApiException;
import com.postech.techchallengefase1.domain.person.entity.Person;
import com.postech.techchallengefase1.domain.person.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person savePerson(Person person) {
        if(!personRepository.save(person)) {
            throw new ApiException("Person already exists", HttpStatus.CONFLICT.value());
        }
        return person;
    }

    public Set<Person> getAllPerson() {
        return personRepository.getPersons();
    }

    public Person getPersonById(Long id) {
        Person person = personRepository.getPersonById(id);
        if(person == null) {
            throw new ApiException("Person not found", HttpStatus.NOT_FOUND.value());
        }
        return personRepository.getPersonById(id);
    }

    public void deletePersonById(Long id) {
        if (!personRepository.deletePersonById(id)) {
            throw new ApiException("Person not found", HttpStatus.NOT_FOUND.value());
        }
    }

    public Person updatePerson(Person person) {
        return personRepository.updatePerson(person).orElseThrow(() ->
                new ApiException("Person not found", HttpStatus.NOT_FOUND.value()));
    }

}

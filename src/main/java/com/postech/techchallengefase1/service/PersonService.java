package com.postech.techchallengefase1.service;

import com.postech.techchallengefase1.domain.Person;
import com.postech.techchallengefase1.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person savePerson(Person person) {
        return personRepository.save(person) ? person : null;
    }

}

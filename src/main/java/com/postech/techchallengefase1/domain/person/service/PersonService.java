package com.postech.techchallengefase1.domain.person.service;

import com.postech.techchallengefase1.domain.exception.ApiException;
import com.postech.techchallengefase1.domain.person.dto.CreatePersonDTO;
import com.postech.techchallengefase1.domain.person.dto.UpdatePersonDTO;
import com.postech.techchallengefase1.domain.person.entity.Person;
import com.postech.techchallengefase1.domain.person.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Person savePerson(CreatePersonDTO person) {
        Person newPerson = personRepository.save(person.getPerson());
        if (newPerson.getId() == null) {
            throw new ApiException("Person already exists", HttpStatus.CONFLICT.value());
        }
        return newPerson;
    }

    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() ->
                new ApiException("Person not found", HttpStatus.NOT_FOUND.value()));
    }

    public void deletePersonById(Long id) {
        if (!personRepository.existsById(id)) {
            throw new ApiException("Person not found", HttpStatus.NOT_FOUND.value());
        }

        personRepository.deleteById(id);
    }

    public Person updatePerson(UpdatePersonDTO person) {
        Person personToUpdate = personRepository.findById(person.getId()).orElseThrow(() ->
                new ApiException("Person not found", HttpStatus.NOT_FOUND.value()));

        Person updatedPerson = new Person();
        updatedPerson.setId(personToUpdate.getId());
        updatedPerson.setName(person.getName() != null ? person.getName() : personToUpdate.getName());
        updatedPerson.setDateOfBirth(person.getDateOfBirth() != null ? person.getDateOfBirth() : personToUpdate.getDateOfBirth());
        updatedPerson.setGender(person.getGender() != null ? person.getGender() : personToUpdate.getGender());
        updatedPerson.setRelationship(person.getRelationship() != null ? person.getRelationship() : personToUpdate.getRelationship());

        return personRepository.save(updatedPerson);
    }

}

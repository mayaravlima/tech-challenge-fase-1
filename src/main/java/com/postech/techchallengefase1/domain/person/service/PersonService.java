package com.postech.techchallengefase1.domain.person.service;

import com.postech.techchallengefase1.domain.address.entity.Address;
import com.postech.techchallengefase1.domain.exception.ApiException;
import com.postech.techchallengefase1.domain.person.dto.CreatePersonDTO;
import com.postech.techchallengefase1.domain.person.dto.PersonWithAddressDTO;
import com.postech.techchallengefase1.domain.person.dto.PersonWithUserDTO;
import com.postech.techchallengefase1.domain.person.dto.UpdatePersonDTO;
import com.postech.techchallengefase1.domain.person.entity.Person;
import com.postech.techchallengefase1.domain.person.enuns.Gender;
import com.postech.techchallengefase1.domain.person.enuns.Relationship;
import com.postech.techchallengefase1.domain.person.repository.PersonRepository;
import com.postech.techchallengefase1.domain.user.entity.User;
import com.postech.techchallengefase1.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    private final UserRepository userRepository;

    private void checkParentRelationship(User user) {
        if (user.getPersons().stream().filter(person -> Relationship.PARENT.equals(person.getRelationship())).count() >= 2) {
            throw new ApiException("Cannot create more than 2 persons with PARENT relationship",
                    HttpStatus.BAD_REQUEST.value());
        }
    }

    private void checkPartnerRelationship(User user) {
        if (user.getPersons().stream().anyMatch(person -> Relationship.PARTNER.equals(person.getRelationship()))) {
            throw new ApiException("Cannot create more than 1 person with PARTNER relationship",
                    HttpStatus.BAD_REQUEST.value());
        }
    }

    private void checkSpouseRelationship(User user) {
        System.out.println(user.getPersons().stream().filter(person -> Relationship.SPOUSE.equals(person.getRelationship())).count());
        if (user.getPersons().stream().anyMatch(person -> Relationship.SPOUSE.equals(person.getRelationship()))) {
            throw new ApiException("Cannot create more than 1 person with SPOUSE relationship",
                    HttpStatus.BAD_REQUEST.value());
        }
    }

    public PersonWithUserDTO savePerson(CreatePersonDTO createPersonDTO, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new ApiException("User not found", HttpStatus.NOT_FOUND.value()));

        if (Relationship.OWNER.equals(createPersonDTO.getRelationship())) {
            throw new ApiException("Cannot create person with OWNER relationship",
                    HttpStatus.BAD_REQUEST.value());
        }

        switch (createPersonDTO.getRelationship()) {
            case PARENT -> checkParentRelationship(user);
            case PARTNER -> checkPartnerRelationship(user);
            case SPOUSE -> checkSpouseRelationship(user);
        }

        Person person = new Person();
        person.setName(createPersonDTO.getName());
        person.setCpf(createPersonDTO.getCpf());
        person.setDateOfBirth(createPersonDTO.getDateOfBirth());
        person.setGender(createPersonDTO.getGender());
        person.setRelationship(createPersonDTO.getRelationship());
        person.setUser(user);

        try {
            personRepository.save(person);
            return PersonWithUserDTO.toDTO(person);
        } catch (DataIntegrityViolationException e) {
            throw new ApiException("CPF already registered", HttpStatus.CONFLICT.value());
        }

    }

    public PersonWithAddressDTO associateAddressWithPerson(String username, Long personId, Long addressId) {

        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ApiException("Person not found", HttpStatus.NOT_FOUND.value()));

        if (!person.getUser().getUsername().equals(username)) {
            throw new ApiException("User not associated with this person", HttpStatus.NOT_FOUND.value());
        }

        Address address = person.getUser().getAddresses().stream()
                .filter(address1 -> Objects.equals(address1.getId(), addressId))
                .findFirst()
                .orElseThrow(() -> new ApiException("Address not found", HttpStatus.NOT_FOUND.value()));

        person.getAddresses().add(address);

        personRepository.save(person);

        return PersonWithAddressDTO.toDto(person);
    }

    public List<PersonWithUserDTO> getAllPerson() {
        List<Person> persons = personRepository.findAll();

        if (persons.isEmpty()) {
            throw new ApiException("No person found", HttpStatus.NOT_FOUND.value());
        }

        return persons.stream().map(PersonWithUserDTO::toDTO).toList();
    }

    public List<PersonWithUserDTO> getPersonByName(String name) {
        List<Person> persons = personRepository.findByName(name).orElseThrow(() ->
                new ApiException("No person found", HttpStatus.NOT_FOUND.value()));

        return persons.stream().map(PersonWithUserDTO::toDTO).toList();

    }

    public List<PersonWithUserDTO> getPersonByRelationship(String relationship) {
        try {
            Relationship convertedRelationship = Relationship.valueOf(relationship.toUpperCase());
            List<Person> persons = personRepository.findByRelationship(convertedRelationship).orElseThrow(() ->
                    new ApiException("No person found", HttpStatus.NOT_FOUND.value()));

            return persons.stream().map(PersonWithUserDTO::toDTO).toList();
        } catch (IllegalArgumentException e) {
            throw new ApiException("Invalid relationship", HttpStatus.BAD_REQUEST.value());
        }
    }

    public List<PersonWithUserDTO> getPersonByGender(String gender) {
        try {
            Gender convertedGender = Gender.valueOf(gender.toUpperCase());
            List<Person> persons = personRepository.findByGender(convertedGender).orElseThrow(() ->
                    new ApiException("No person found", HttpStatus.NOT_FOUND.value()));

            return persons.stream().map(PersonWithUserDTO::toDTO).toList();
        } catch (IllegalArgumentException e) {
            throw new ApiException("Invalid gender", HttpStatus.BAD_REQUEST.value());
        }
    }

    public List<PersonWithUserDTO> getPersonByCpf(String cpf) {

        List<Person> persons = personRepository.findByCpf(cpf).orElseThrow(() ->
                new ApiException("No person found", HttpStatus.NOT_FOUND.value()));

        return persons.stream().map(PersonWithUserDTO::toDTO).toList();

    }

    public PersonWithUserDTO getPersonById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() ->
                new ApiException("Person not found", HttpStatus.NOT_FOUND.value()));

        return PersonWithUserDTO.toDTO(person);
    }

    public void deletePersonById(Long id) {
        if (!personRepository.existsById(id)) {
            throw new ApiException("Person not found", HttpStatus.NOT_FOUND.value());
        }

        if (Relationship.OWNER.equals(personRepository.findById(id).get().getRelationship())) {
            throw new ApiException("Cannot delete person with OWNER relationship",
                    HttpStatus.BAD_REQUEST.value());
        }

        personRepository.deleteById(id);
    }

    public PersonWithUserDTO updatePerson(UpdatePersonDTO person, Long id) {
        if (person.getRelationship() != null && Relationship.OWNER.equals(person.getRelationship())) {
            throw new ApiException("Cannot update person with OWNER relationship",
                    HttpStatus.BAD_REQUEST.value());
        }

        Person personToUpdate = personRepository.findById(id).orElseThrow(() ->
                new ApiException("Person not found", HttpStatus.NOT_FOUND.value()));

        if (person.getName() != null) {
            personToUpdate.setName(person.getName());
        }
        if (person.getDateOfBirth() != null) {
            personToUpdate.setDateOfBirth(person.getDateOfBirth());
        }
        if (person.getGender() != null) {
            personToUpdate.setGender(person.getGender());
        }
        if (person.getRelationship() != null) {
            personToUpdate.setRelationship(person.getRelationship());
        }

        personRepository.save(personToUpdate);

        return PersonWithUserDTO.toDTO(personToUpdate);
    }

}

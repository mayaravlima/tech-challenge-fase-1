package com.postech.techchallengefase1.domain.user.service;

import com.postech.techchallengefase1.domain.exception.ApiException;
import com.postech.techchallengefase1.domain.person.entity.Person;
import com.postech.techchallengefase1.domain.person.enuns.Relationship;
import com.postech.techchallengefase1.domain.user.dto.CreateUserDTO;
import com.postech.techchallengefase1.domain.user.entity.User;
import com.postech.techchallengefase1.domain.user.repository.UserRepository;
import jakarta.persistence.PrePersist;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUserWithPerson(CreateUserDTO createUserDTO) {
        System.out.println(userRepository.findByPerson_Name(createUserDTO.getPerson().getName()));

        if (Relationship.OWNER.compareTo(createUserDTO.getPerson().getRelationship()) != 0) {
            throw new ApiException("Only OWNER person can create user", HttpStatus.CONFLICT.value());
        }

        User user = new User();
        user.setUsername(createUserDTO.getUsername());

        Person person = new Person();
        person.setName(createUserDTO.getPerson().getName());
        person.setDateOfBirth(createUserDTO.getPerson().getDateOfBirth());
        person.setGender(createUserDTO.getPerson().getGender());
        person.setRelationship(createUserDTO.getPerson().getRelationship());

        Set<Person> newPerson = new HashSet<>(List.of(person));
        user.setPerson(newPerson);

        return userRepository.save(user);
    }
}

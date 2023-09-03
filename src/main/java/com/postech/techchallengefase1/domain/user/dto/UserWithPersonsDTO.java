package com.postech.techchallengefase1.domain.user.dto;

import com.postech.techchallengefase1.domain.person.dto.PersonDTO;
import com.postech.techchallengefase1.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
public class UserWithPersonsDTO {
    private Long id;
    private String username;
    private String email;
    private Set<PersonDTO> persons;

    public static UserWithPersonsDTO toDTO(User user) {
        Set<PersonDTO> personDTOs = user.getPersons().stream()
                .map(person -> new PersonDTO(person.getId(),
                        person.getName(),
                        person.getCpf(),
                        person.getDateOfBirth(),
                        person.getGender(),
                        person.getRelationship()))
                .collect(Collectors.toSet());

        return new UserWithPersonsDTO(user.getId(),
                user.getUsername(),
                user.getEmail(),
                personDTOs);
    }
}

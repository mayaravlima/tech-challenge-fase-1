package com.postech.techchallengefase1.domain.person.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.postech.techchallengefase1.domain.person.entity.Person;
import com.postech.techchallengefase1.domain.person.enuns.Gender;
import com.postech.techchallengefase1.domain.person.enuns.Relationship;
import com.postech.techchallengefase1.domain.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonWithUserDTO {
    private Long id;
    private String name;
    private String cpf;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    private Gender gender;
    private Relationship relationship;
    private UserDTO user;

    public static PersonWithUserDTO toDTO(Person person) {

        UserDTO userDTO = new UserDTO(person.getUser().getId(),
                person.getUser().getUsername(),
                person.getUser().getEmail());

        return new PersonWithUserDTO(person.getId(),
                person.getName(),
                person.getCpf(),
                person.getDateOfBirth(),
                person.getGender(),
                person.getRelationship(),
                userDTO);
    }
}

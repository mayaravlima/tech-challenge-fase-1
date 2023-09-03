package com.postech.techchallengefase1.domain.person.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.postech.techchallengefase1.domain.person.enuns.Gender;
import com.postech.techchallengefase1.domain.person.enuns.Relationship;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class PersonDTO {
    private Long id;
    private String name;
    private String cpf;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    private Gender gender;
    private Relationship relationship;
}

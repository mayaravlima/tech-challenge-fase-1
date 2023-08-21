package com.postech.techchallengefase1.domain.person.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.postech.techchallengefase1.domain.person.enuns.Gender;
import com.postech.techchallengefase1.domain.person.enuns.Relationship;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePersonDTO {

    @NotNull(message = "Id can't be empty or null")
    @Positive(message = "Id must be more than 0")
    private Long id;

    @Pattern(regexp = "[a-zA-Z\\s-]+", message = "Name must contain only letters, spaces, or hyphens")
    @Size(min = 5, max = 50, message = "Name must be between 5 and 50 characters")
    private String name;

    @Past(message = "Date of birth must be in the past")
    @JsonProperty("date_of_birth")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    private Gender gender;

    private Relationship relationship;

}

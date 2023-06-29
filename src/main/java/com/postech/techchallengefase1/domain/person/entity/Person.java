package com.postech.techchallengefase1.domain.person.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.postech.techchallengefase1.domain.person.enuns.Relationship;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@EqualsAndHashCode(exclude = {"id", "relationship", "gender"})
@Getter
@Setter
public class Person {

    private Long id;

    @NotNull(message = "Name can't be empty or null")
    @Pattern(regexp = "[a-zA-Z\\s-]+", message = "Name must contain only letters, spaces, or hyphens")
    @Size(min = 5, max = 50, message = "Name must be between 5 and 50 characters")
    private String name;

    @NotNull(message = "Date of birth name can't be empty or null")
    @Past(message = "Date of birth must be in the past")
    @JsonProperty("date_of_birth")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @Pattern(regexp = "(?i)[FM]", message = "The gender must be F ou M")
    private String gender;

    private Relationship relationship;

}

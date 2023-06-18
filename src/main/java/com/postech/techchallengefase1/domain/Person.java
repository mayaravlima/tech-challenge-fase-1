package com.postech.techchallengefase1.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@EqualsAndHashCode(exclude = {"id"})
@Getter
@Setter
public class Person {

    private Long id;

    @NotNull(message = "Name can't be empty or null")
    @Size(min = 5, max = 50, message = "Name must be between 5 and 50 characters")
    @JsonProperty
    private String name;

    @NotNull(message = "Date of birth name can't be empty or null")
    @Past(message = "Date of birth must be in the past")
    @JsonProperty("date_of_birth")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Gender must not be empty or null")
    @JsonProperty
    private String gender;

    @NotEmpty(message = "Relationship must not be empty or null")
    @JsonProperty
    private String relationship;

}

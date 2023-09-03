package com.postech.techchallengefase1.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.postech.techchallengefase1.domain.person.enuns.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

    @NotNull(message = "Username can't be empty or null")
    @Pattern(regexp = "[a-zA-Z\\s-]+", message = "Username must contain only letters, spaces, or hyphens")
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    private String username;

    @NotNull(message = "Email can't be empty or null")
    @Email(message = "Invalid email")
    private String email;

    @NotNull(message = "Name can't be empty or null")
    @Pattern(regexp = "[a-zA-Z\\s-]+", message = "Name must contain only letters, spaces, or hyphens")
    @Size(min = 5, max = 50, message = "Name must be between 5 and 50 characters")
    private String name;

    @NotNull(message = "CPF can't be empty or null")
    @CPF(message = "Invalid CPF")
    private String cpf;

    @NotNull(message = "Date of birth name can't be empty or null")
    @Past(message = "Date of birth must be in the past")
    @JsonProperty("date_of_birth")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    private Gender gender;

}

package com.postech.techchallengefase1.domain.user.dto;

import com.postech.techchallengefase1.domain.person.dto.CreatePersonDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

    @NotNull(message = "Username can't be empty or null")
    @Pattern(regexp = "[a-zA-Z\\s-]+", message = "Username must contain only letters, spaces, or hyphens")
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    private String username;

    private CreatePersonDTO person;
}

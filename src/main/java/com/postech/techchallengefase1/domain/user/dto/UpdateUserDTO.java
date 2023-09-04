package com.postech.techchallengefase1.domain.user.dto;

import jakarta.validation.constraints.Email;
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
public class UpdateUserDTO {

    @Pattern(regexp = "^[a-zA-Z0-9\\-_]+$", message = "Username must contain only letters, spaces, or hyphens")
    @Size(min = 3, max = 50, message = "Username must be between 5 and 50 characters")
    private String username;

    @Email(message = "Invalid email")
    private String email;

}

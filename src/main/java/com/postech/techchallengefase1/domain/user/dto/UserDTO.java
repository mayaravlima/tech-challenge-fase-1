package com.postech.techchallengefase1.domain.user.dto;

import com.postech.techchallengefase1.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String email;

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getId(),
                user.getUsername(),
                user.getEmail());
    }
}

package com.postech.techchallengefase1.domain.user.controller;

import com.postech.techchallengefase1.domain.user.dto.CreateUserDTO;
import com.postech.techchallengefase1.domain.user.entity.User;
import com.postech.techchallengefase1.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public User save(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return userService.createUserWithPerson(createUserDTO);
    }

}

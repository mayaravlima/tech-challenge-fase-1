package com.postech.techchallengefase1.domain.user.controller;

import com.postech.techchallengefase1.domain.user.dto.CreateUserDTO;
import com.postech.techchallengefase1.domain.user.dto.UpdateUserDTO;
import com.postech.techchallengefase1.domain.user.dto.UserWithPersonsDTO;
import com.postech.techchallengefase1.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserWithPersonsDTO> saveUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(createUserDTO));
    }

    @GetMapping("/search")
    public UserWithPersonsDTO getUserByParams(@RequestParam(required = false) String username,
                                              @RequestParam(required = false) String email) {
        return userService.getUserWithParams(username, email);
    }

    @GetMapping
    public List<UserWithPersonsDTO> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public UserWithPersonsDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "User deleted successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserWithPersonsDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, updateUserDTO));
    }

}

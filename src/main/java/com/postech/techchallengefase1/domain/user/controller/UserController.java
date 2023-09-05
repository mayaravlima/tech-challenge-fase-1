package com.postech.techchallengefase1.domain.user.controller;

import com.postech.techchallengefase1.domain.user.dto.CreateUserDTO;
import com.postech.techchallengefase1.domain.user.dto.UpdateUserDTO;
import com.postech.techchallengefase1.domain.user.dto.UserResponseDTO;
import com.postech.techchallengefase1.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "User", description = "Controller responsible for user operations")
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Create a new user")
    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(createUserDTO));
    }

    @GetMapping("/search")
    public UserResponseDTO getUserByParams(@RequestParam(required = false) String username,
                                           @RequestParam(required = false) String email) {
        return userService.getUserWithParams(username, email);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "User deleted successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, updateUserDTO));
    }

}

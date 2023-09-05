package com.postech.techchallengefase1.domain.user.service;

import com.postech.techchallengefase1.domain.exception.ApiException;
import com.postech.techchallengefase1.domain.person.dto.PersonDTO;
import com.postech.techchallengefase1.domain.person.entity.Person;
import com.postech.techchallengefase1.domain.person.enuns.Relationship;
import com.postech.techchallengefase1.domain.person.repository.PersonRepository;
import com.postech.techchallengefase1.domain.user.dto.*;
import com.postech.techchallengefase1.domain.user.entity.User;
import com.postech.techchallengefase1.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;

    private void checkIfUsernameExists(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new ApiException("Username already exists", 409);
        }
    }

    private void checkIfUserEmailExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ApiException("Email already exists", 409);
        }
    }

    private void checkIfUserCPF(String cpf) {
        if (personRepository.existsByCpf(cpf)) {
            throw new ApiException("CPF already exists", 409);
        }
    }

    private User createUser(CreateUserDTO createUserDTO) {
        User user = new User();
        user.setUsername(createUserDTO.getUsername());
        user.setEmail(createUserDTO.getEmail());
        return user;
    }

    private Person createPerson(CreateUserDTO createUserDTO, User user) {
        Person person = new Person();
        person.setName(createUserDTO.getName());
        person.setCpf(createUserDTO.getCpf());
        person.setDateOfBirth(createUserDTO.getDateOfBirth());
        person.setGender(createUserDTO.getGender());
        person.setRelationship(Relationship.OWNER);
        person.setUser(user);
        return person;
    }

    private UserResponseDTO createUserResponse(User user, Person person) {
        return new UserResponseDTO(
                UserDTO.toDTO(user),
                Set.of(PersonDTO.toDTO(person)),
                new HashSet<>(),
                new HashSet<>()
        );
    }


    @Transactional
    public UserResponseDTO saveUser(CreateUserDTO createUserDTO) {
        try {
            checkIfUsernameExists(createUserDTO.getUsername());
            checkIfUserEmailExists(createUserDTO.getEmail());
            checkIfUserCPF(createUserDTO.getCpf());

            User user = createUser(createUserDTO);
            Person person = createPerson(createUserDTO, user);

            userRepository.save(user);
            personRepository.save(person);

            return createUserResponse(user, person);
        } catch (DataIntegrityViolationException e) {
            throw new ApiException("User already exists", 409);
        }
    }

    public UserResponseDTO getUserWithParams(String username, String email) {
        User user;
        if (Objects.isNull(username) && Objects.isNull(email)) {
            throw new ApiException("You must provide at least one parameter", 400);
        } else if (Objects.isNull(username)) {
            user = userRepository.findByEmailContainingIgnoreCase(email).orElseThrow(() ->
                    new ApiException("User not found", 404));
        } else if (Objects.isNull(email)) {
            user = userRepository.findByUsernameContainingIgnoreCase(username).orElseThrow(() ->
                    new ApiException("User not found", 404));
        } else {
            user = userRepository.findByUsernameAndEmailContainingIgnoreCase(username, email).orElseThrow(() ->
                    new ApiException("User not found", 404));
        }

        return UserResponseDTO.toDTO(user);
    }

    public List<UserResponseDTO> getAllUser() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new ApiException("No users found", 404);
        }

        return users.stream()
                .map(UserResponseDTO::toDTO)
                .toList();
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ApiException("User not found", 404));

        return UserResponseDTO.toDTO(user);
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ApiException("User not found", 404));

        userRepository.delete(user);
    }

    public UserResponseDTO updateUser(Long id, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ApiException("User not found", 404));

        if (updateUserDTO.getUsername() != null) {
            checkIfUsernameExists(updateUserDTO.getUsername());
            user.setUsername(updateUserDTO.getUsername());
        }
        if (updateUserDTO.getEmail() != null) {
            checkIfUserEmailExists(updateUserDTO.getEmail());
            user.setEmail(updateUserDTO.getEmail());
        }

        try {
            userRepository.save(user);
            return UserResponseDTO.toDTO(user);
        } catch (DataIntegrityViolationException e) {
            throw new ApiException("User already exists", 409);
        }
    }
}

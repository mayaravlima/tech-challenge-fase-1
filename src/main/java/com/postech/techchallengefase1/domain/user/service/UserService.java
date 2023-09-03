package com.postech.techchallengefase1.domain.user.service;

import com.postech.techchallengefase1.domain.exception.ApiException;
import com.postech.techchallengefase1.domain.person.dto.PersonDTO;
import com.postech.techchallengefase1.domain.person.entity.Person;
import com.postech.techchallengefase1.domain.person.enuns.Relationship;
import com.postech.techchallengefase1.domain.person.repository.PersonRepository;
import com.postech.techchallengefase1.domain.user.dto.CreateUserDTO;
import com.postech.techchallengefase1.domain.user.dto.UpdateUserDTO;
import com.postech.techchallengefase1.domain.user.dto.UserWithPersonsDTO;
import com.postech.techchallengefase1.domain.user.entity.User;
import com.postech.techchallengefase1.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;

    @Transactional
    public UserWithPersonsDTO createUser(CreateUserDTO createUserDTO) {
        try {
            User user = new User();
            user.setUsername(createUserDTO.getUsername());
            user.setEmail(createUserDTO.getEmail());

            userRepository.save(user);

            Person person = new Person();
            person.setName(createUserDTO.getName());
            person.setCpf(createUserDTO.getCpf());
            person.setDateOfBirth(createUserDTO.getDateOfBirth());
            person.setGender(createUserDTO.getGender());
            person.setRelationship(Relationship.OWNER);
            person.setUser(user);

            personRepository.save(person);

            return new UserWithPersonsDTO(user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    Set.of(new PersonDTO(person.getId(),
                            person.getName(),
                            person.getCpf(),
                            person.getDateOfBirth(),
                            person.getGender(),
                            person.getRelationship())));
        } catch (DataIntegrityViolationException e) {
            throw new ApiException("User already exists", 409);
        }
    }

    public UserWithPersonsDTO getUserWithParams(String username, String email) {
        User user;
        if (Objects.isNull(username) && Objects.isNull(email)) {
            throw new ApiException("You must provide at least one parameter", 400);
        } else if (Objects.isNull(username)) {
            user = userRepository.findByEmail(email).orElseThrow(() ->
                    new ApiException("User not found", 404));
        } else if (Objects.isNull(email)) {
            user = userRepository.findByUsername(username).orElseThrow(() ->
                    new ApiException("User not found", 404));
        } else {
            user = userRepository.findByUsernameAndEmail(username, email).orElseThrow(() ->
                    new ApiException("User not found", 404));
        }

        return UserWithPersonsDTO.toDTO(user);
    }

    public List<UserWithPersonsDTO> getAllUser() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new ApiException("No users found", 404);
        }

        return users.stream()
                .map(UserWithPersonsDTO::toDTO)
                .toList();
    }

    public UserWithPersonsDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ApiException("User not found", 404));

        return UserWithPersonsDTO.toDTO(user);
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ApiException("User not found", 404));

        userRepository.delete(user);
    }

    public UserWithPersonsDTO updateUser(Long id, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ApiException("User not found", 404));

        user.setUsername(Objects.nonNull(updateUserDTO.getUsername()) ? updateUserDTO.getUsername() : user.getUsername());
        user.setEmail(Objects.nonNull(updateUserDTO.getEmail()) ? updateUserDTO.getEmail() : user.getEmail());

        try {
            userRepository.save(user);
            return UserWithPersonsDTO.toDTO(user);
        } catch (DataIntegrityViolationException e) {
            throw new ApiException("User already exists", 409);
        }
    }
}

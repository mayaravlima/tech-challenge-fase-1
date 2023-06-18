package com.postech.techchallengefase1.controller;

import com.postech.techchallengefase1.domain.Person;
import com.postech.techchallengefase1.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Person person) {
        Person result = service.savePerson(person);
        return result == null ?
                ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "This person already exists")) :
                ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}

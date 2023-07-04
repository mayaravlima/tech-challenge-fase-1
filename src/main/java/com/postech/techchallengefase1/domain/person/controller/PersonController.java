package com.postech.techchallengefase1.domain.person.controller;

import com.postech.techchallengefase1.domain.person.service.PersonService;
import com.postech.techchallengefase1.domain.person.entity.Person;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Person> save(@Valid @RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.savePerson(person));
    }

    @GetMapping
    public ResponseEntity<Set<Person>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllPerson());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPersonById(id));
    }

    @PutMapping
    public ResponseEntity<Person> update(@Valid @RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updatePerson(person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id) {
        service.deletePersonById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Person deleted successfully"));
    }
}
